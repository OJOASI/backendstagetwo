package com.atos.ojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BatchbackendStagetwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchbackendStagetwoApplication.class, args);
	}
}
