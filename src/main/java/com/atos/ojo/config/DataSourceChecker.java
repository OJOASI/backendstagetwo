package com.atos.ojo.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataSourceChecker {

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void checkDataSources() {
		String[] dataSourceBeanNames = applicationContext.getBeanNamesForType(DataSource.class);

		log.info("DataSources in the application context:");
		for (String beanName : dataSourceBeanNames) {
			log.info(beanName);
		}
	}
}