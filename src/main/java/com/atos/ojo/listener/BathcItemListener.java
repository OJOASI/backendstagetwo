package com.atos.ojo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class BathcItemListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {

		/*
		 * log.info(">>>>> JOB " + jobExecution.getJobInstance().getJobName() +
		 * " started at " + jobExecution.getStartTime());
		 */

	}

	@Override
	public void afterJob(JobExecution jobExecution) {

		/*
		 * 
		 * String exitCode = jobExecution.getExitStatus().getExitCode();
		 * log.info("<<<<< JOB " + jobExecution.getJobInstance().getJobName() +
		 * " finished at " + jobExecution.getEndTime() + " with status " + exitCode);
		 */
	}
}
