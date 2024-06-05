package com.atos.ojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChunkSizeService {

	@Value("${ocm.validatedorders.limit:100}")
	private Integer ocmOrdersLimit;

	@Value("${ocm.validatedorders.count.query}")
	private String ocmValidatedOrdersCountQuery;

	@Value("${stagetwo.batchitem.query.initalcount}")
	private String batchItemsInitialRequestsCountQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer getAllowedChunkSize() {
		Integer ocmOrdersCount = getOcmValidatedOrdersCount();
		Integer chunkSize = ocmOrdersLimit - ocmOrdersCount < 0 ? 0 : ocmOrdersLimit - ocmOrdersCount;
		log.info("OCM: current validated orders=" + ocmOrdersCount + " ,Limit=" + ocmOrdersLimit + " ,allowd chunk="
				+ chunkSize);
		return chunkSize;
	}

	private Integer getOcmValidatedOrdersCount() {
		return jdbcTemplate.queryForObject(ocmValidatedOrdersCountQuery, Integer.class);
	}

	public Integer getBatchItemInitialRequestsCount() {
		Integer intialRequestsCount = jdbcTemplate.queryForObject(batchItemsInitialRequestsCountQuery, Integer.class);
		log.info("AMOM/ACDM: Initial requests=" + intialRequestsCount);
		return intialRequestsCount;
	}

}
