package com.atos.ojo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atos.ojo.model.batch.BatchItem;
import com.atos.ojo.model.lizard.Error;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class BatchUtil {

	@Value("${stagetwo.batchitem.error.length:1000}")
	private Integer errorMessageLength;

	@Value("${stagetwo.batchitem.status.failed:Failed}")
	private String failedStatus;

	@Value("${stagetwo.batchitem.retry.limit:5}")
	private Integer retryLimit;

	@Value("${stagetwo.batchitem.status.error:Failed}")
	private String errorStatus;

	public String clobToString(Clob clob) throws SQLException, IOException {

		if (clob == null) {
			return null;
		}
		;
		StringBuilder sb = new StringBuilder();
		try (Reader reader = clob.getCharacterStream(); BufferedReader br = new BufferedReader(reader)) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		return sb.toString();
	}

	public void setItemErrorAttributes(BatchItem item, String errorMessage) {
		String truncatedErrorMessage = errorMessage.length() > errorMessageLength
				? errorMessage.substring(0, errorMessageLength)
				: errorMessage;
		Integer retryCount = item.getRetry();
		if(retryCount != null)
			item.setRetry(retryCount + 1);
		else
			item.setRetry(1);
		item.setStatus(retryCount > retryLimit ? failedStatus : errorStatus);
		item.setErrorMsg(truncatedErrorMessage);
	}

}
