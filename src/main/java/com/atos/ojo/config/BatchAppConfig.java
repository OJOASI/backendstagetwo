package com.atos.ojo.config;

import java.time.Duration;

import javax.sql.DataSource;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BatchAppConfig {

	@Value("${ignore.ssl:true}")
	private boolean ignoreSSLCheck;

	@Value("${rest.template.read.timeout:30s}")
	private Duration readTimeout;

	@Value("${rest.template.connect.timeout:30s}")
	private Duration connectTimeout;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		return objectMapper;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory());
		restTemplate.setRequestFactory(new CustomClientHttpRequestFactory());

		return restTemplate;
	}

	private HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(Integer.parseInt(connectTimeout.getSeconds() + "000"));
		factory.setReadTimeout(Integer.parseInt(readTimeout.getSeconds() + "000"));
		return factory;
	}

}
