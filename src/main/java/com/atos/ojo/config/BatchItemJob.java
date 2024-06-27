package com.atos.ojo.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.atos.ojo.listener.BathcItemListener;
import com.atos.ojo.model.batch.BatchItem;
import com.atos.ojo.processor.BatchItemProcessor;
import com.atos.ojo.service.ChunkSizeService;
import com.atos.ojo.util.BatchUtil;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@Slf4j
@EnableScheduling

public class BatchItemJob {

	@Autowired
	@Qualifier("batchDataSource")
	private DataSource batchDataSource;

	@Autowired
	@Qualifier("ocmDataSource")
	private DataSource OCMDataSource;

	@Autowired
	BatchItemProcessor batchItemProcessor;

	@Autowired
	BathcItemListener bathcItemListener;

	@Autowired
	ChunkSizeService chunkSizeService;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	BatchUtil batchUtil;

	@Value("${stagetwo.batchitem.status.inital:VALID}")
	private String validStatus;

	@Value("${stagetwo.batchitem.status.error:ERROR}")
	private String errorStatus;

	@Value("${stagetwo.batchitem.retry.limit:5}")
	private Integer retryLimit;

	@Value("${stagetwo.batch.chunksize:100}")
	private Integer chunkSize;

	@Value("${stagetwo.batch.throttleLimit:1}")
	private Integer throttleLimit;

	@Value("${stagetwo.taskexecutor.corePoolSize:4}")
	private Integer corePoolSize;

	@Value("${stagetwo.taskexecutor.maxPoolSize:20}")
	private Integer maxPoolSize;

	// JOB Bean
	@Bean
	@Scope("prototype")
	public Job batchItemsJob(Integer allowedChunkSize) throws Exception {
		return jobBuilderFactory.get("stageTwoJob").flow(batchItemsStep(allowedChunkSize)).end()
				.listener(bathcItemListener).build();
	}

	@Bean
	@Scope("prototype")
	public Step batchItemsStep(Integer allowedChunkSize) throws Exception {

		return stepBuilderFactory.get("batchItemsStep").<BatchItem, BatchItem>chunk(chunkSize)
				.reader(batchItemReader(allowedChunkSize)).processor(batchItemProcessor).writer(batchItemWriter())
				.taskExecutor(parallelTaskExecutor()).throttleLimit(throttleLimit).allowStartIfComplete(true).build();
	}

	/*
	 * ******************* Task executor
	 ********************/
	@Bean
	public TaskExecutor parallelTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setThreadNamePrefix("batchitem-");
		executor.initialize();
		return executor;
	}

	/*
	 * ******************* Batch Item Reader
	 ********************/

	@Bean
	@Scope("prototype")
	public JdbcPagingItemReader<BatchItem> batchItemReader(Integer allowedChunkSize) throws Exception {
		// int pageSize = chunkSizeService.getAllowedChunkSize();
		JdbcPagingItemReader<BatchItem> reader = new JdbcPagingItemReader<BatchItem>();
		reader.setDataSource(batchDataSource);
		reader.setPageSize(allowedChunkSize);
		reader.setQueryProvider(createQueryProvider());
		reader.setRowMapper(new BeanPropertyRowMapper<>(BatchItem.class));
		Map<String, Object> parameterValues = new HashMap<>();
		parameterValues.put("validStatus", validStatus);
		parameterValues.put("errorStatus", errorStatus);
		parameterValues.put("retryLimit", retryLimit);
		reader.setParameterValues(parameterValues);
		reader.afterPropertiesSet();
		return reader;
	}

	@Bean
	public PagingQueryProvider createQueryProvider() throws Exception {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(batchDataSource);
		queryProvider.setSelectClause("*");
		queryProvider.setFromClause("BATCH_ITEM");
		queryProvider.setWhereClause("WHERE status=:validStatus or (status =:errorStatus and retry < :retryLimit)");
		queryProvider.setSortKeys(sortKeys());

		return queryProvider.getObject();
	}

	private Map<String, Order> sortKeys() {
		Map<String, Order> sortConfiguration = new LinkedHashMap<>();
		sortConfiguration.put("PRIORITY", Order.DESCENDING);
		sortConfiguration.put("ITEM_ID", Order.ASCENDING);
		return sortConfiguration;
	}

	/*
	 * ******************* Batch Item Writer
	 ********************/
	@Bean
	public ItemWriter<BatchItem> batchItemWriter() {
		JdbcBatchItemWriter<BatchItem> writer = new JdbcBatchItemWriter<BatchItem>();
		writer.setDataSource(batchDataSource);
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql(
				"UPDATE batch_item SET enrich_data = ? ,po_data = ?, update_date = ?, status = ?,error_msg = ?,retry = ? "
						+ " WHERE batch_id = ?  AND item_id = ?");

		// LobHandler for handling CLOB data
		LobHandler lobHandler = new DefaultLobHandler();

		// Inline implementation of ItemPreparedStatementSetter
		writer.setItemPreparedStatementSetter((item, ps) -> {
			try {
				lobHandler.getLobCreator().setClobAsString(ps, 1, batchUtil.clobToString(item.getEnrichData()));
				lobHandler.getLobCreator().setClobAsString(ps, 2, (batchUtil.clobToString(item.getPoData())));

			} catch (IOException e) {
				e.printStackTrace();
			}

			Timestamp updateTimestamp = new Timestamp(item.getUpdateDate().getTime());
			ps.setTimestamp(3, updateTimestamp);
			ps.setString(4, item.getStatus()); //
			ps.setString(5, item.getErrorMsg()); //
			ps.setInt(6, item.getRetry());
			ps.setInt(7, item.getBatchId());
			ps.setInt(8, item.getItemId());

		});

		writer.afterPropertiesSet();
		return writer;
	}

	@Scheduled(initialDelayString = "${stagetwo.job.initial-delay}", fixedRateString = "${stagetwo.job.fixed-delay}")
	public void launchJob() throws Exception {
		if (chunkSizeService.getBatchItemInitialRequestsCount() > 0) {
			Integer allowedChunkSize = chunkSizeService.getAllowedChunkSize();
			if (allowedChunkSize > 0) {
				log.info("Triggering Stage 2 job.");

				jobLauncher.run(batchItemsJob(allowedChunkSize),
						new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());

			} else {
				log.info("Stage 2 Job skipped: Initial requests found, but Lizard/OCM congested !");
			}

		} else {
			log.info("Stage 2 Job skipped: No initial requests found.");
		}

	}

}