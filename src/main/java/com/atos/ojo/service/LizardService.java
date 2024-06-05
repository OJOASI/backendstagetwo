package com.atos.ojo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.atos.ojo.model.batch.BatchItem;
import com.atos.ojo.model.lizard.ProductOrder;
import com.atos.ojo.model.lizard.ProductOrderCreate;
import com.atos.ojo.util.BatchUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LizardService {

	@Autowired
	ObjectMapper objectmapper;

	@Autowired
	BatchUtil batchUtil;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@Value("${lizard.po.url}")
	private String lizardPoUrl;

	@Value("${stagetwo.batchitem.status.success}")
	private String successStatus;

	// Lizard API

	public void callLizardProductOrderApi(ProductOrderCreate productOrderCreate, Map<String, String> enrichMap,
			BatchItem item) {

		// parse the input
		String productOrderCreateString = "";
		try {
			productOrderCreateString = objectmapper.writeValueAsString(productOrderCreate);
		} catch (JsonProcessingException ex) {
			// extract error
			String errorMessage = "Failed to convert PO Object to Json String: " + ex.getMessage() != null
					? ex.getMessage().replaceAll("\\n", "")
					: null;
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
			return;
		}

		// call Lizard product Order API.
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<>(productOrderCreateString, headers);
			log.info("--> Lizard PO API Request: {} {} | Body: {}", HttpMethod.POST, lizardPoUrl,
					productOrderCreateString);
			ResponseEntity<ProductOrder> response = restTemplate.exchange(lizardPoUrl, HttpMethod.POST, requestEntity,
					ProductOrder.class);
			try {
				log.info("<-- Lizard PO API Response: Status: {} | Body: {}", response.getStatusCode(),
						objectmapper.writeValueAsString(response.getBody()));
			} catch (JsonProcessingException ex) {
				// extract error
				String errorMessage = "Failed to convert Lizard PO response to String: " + ex.getMessage() != null
						? ex.getMessage().replaceAll("\\n", "")
						: null;
				log.error(errorMessage);
				batchUtil.setItemErrorAttributes(item, errorMessage);
				ex.printStackTrace();
			}
			// Handle successful response
			ProductOrder productOrder = response.getBody();
			item.setStatus(successStatus);
			item.setErrorMsg(productOrder.getId());
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			// extract error
			String errorMessage = "Lizard PO API returned an error: " + ex.getStatusCode().value() + "-"
					+ (ex.getResponseBodyAsString() != null ? ex.getResponseBodyAsString().replaceAll("\\n", "")
							: null);

			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			// ex.printStackTrace();
		} catch (Exception ex) {
			// extract error
			String errorMessage = "Lizard PO API throws an exception: " + ex.getMessage() != null
					? ex.getMessage().replaceAll("\\n", "")
					: null;
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
		}

	}

}
