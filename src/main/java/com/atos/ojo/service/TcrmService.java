package com.atos.ojo.service;

import java.sql.Clob;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;

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
import com.atos.ojo.model.tcrm.CreateOrganizationPartyResult;
import com.atos.ojo.model.tcrm.Identification;
import com.atos.ojo.model.tcrm.TcrmOrganizationParty;
import com.atos.ojo.model.tcrm.TcrmOrganizationPartyResponse;
import com.atos.ojo.util.BatchUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TcrmService {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	BatchUtil batchUtil;

	@Value("${tcrm.party.url}")
	private String tcrmPartyUrl;
	
	@Value("${stagetwo.batchitem.status.inprogress}")
	private String inProgress;

	public void callCreateOrganizationParty(Map<String, String> enrichMap, BatchItem item) {

		// build OrganizationParty object
		String identificationId = enrichMap.get("companyRegistrationNumber");
		String parentAccountId = enrichMap.get("TCRMParentId");
		// String email = enrichMap.get("email");
		String mobilePhone = enrichMap.get("mobilePhone");
		String organizationName = enrichMap.get("tradingName");

		TcrmOrganizationParty tcrmOrganizationParty = new TcrmOrganizationParty();
		Identification identification = new Identification();
		identification.setIdentificationId(identificationId);
		identification.setType("CompanyRegistrationNumber");
		tcrmOrganizationParty.setIdentification(identification);
		// tcrmOrganizationParty.setEmail(email);
		tcrmOrganizationParty.setMarketType("mobile");
		tcrmOrganizationParty.setMobilePhone(mobilePhone);
		tcrmOrganizationParty.setOrganizationName(organizationName);
		tcrmOrganizationParty.setParentAccountId(parentAccountId);
		tcrmOrganizationParty.setStatus("Active");

		// Call TCRM API
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<TcrmOrganizationParty> requestEntity = new HttpEntity<>(tcrmOrganizationParty, headers);
			log.info("--> TCRM Party API Request: {} {} | Body: {}", HttpMethod.POST, tcrmPartyUrl,
					objectMapper.writeValueAsString(tcrmOrganizationParty));

			ResponseEntity<TcrmOrganizationPartyResponse> response = restTemplate.exchange(tcrmPartyUrl,
					HttpMethod.POST, requestEntity, TcrmOrganizationPartyResponse.class);
			log.info("<-- TCRM Party API Response: Status: {} | Body: {}", response.getStatusCode(),
					objectMapper.writeValueAsString(response.getBody()));

			if (response.getBody() != null && response.getBody().getCreateOrganizationPartyResult() != null) {
				CreateOrganizationPartyResult partyResponse = response.getBody().getCreateOrganizationPartyResult();

				if (partyResponse.getIsSuccess()) {
					if (partyResponse.getId() != null && !partyResponse.getId().isEmpty()) {
						enrichMap.put("TcrmId", partyResponse.getId());
						Clob enrichDataClob = new SerialClob(
								objectMapper.writeValueAsString(enrichMap).toString().toCharArray());
						item.setEnrichData(enrichDataClob);
						item.setStatus(inProgress);
						item.setErrorMsg("TCRM Party Successfully Created");
					} else {
						String errorMessage = "The TcrmOrganizationParty response indicates Success but no TCRM ID generated -"
								+ partyResponse.getMessage();
						log.error(errorMessage);
						batchUtil.setItemErrorAttributes(item, errorMessage);
					}

				} else {
					String errorMessage = "The TcrmOrganizationParty response indicates failure -"
							+ partyResponse.getMessage();
					log.error(errorMessage);
					batchUtil.setItemErrorAttributes(item, errorMessage);
				}

			} else {
				String errorMessage = "The TcrmOrganizationParty response is NULL/Empty";
				log.error(errorMessage);
				batchUtil.setItemErrorAttributes(item, errorMessage);
			}
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			// extract error
			String errorMessage = "TcrmOrganizationParty  API returned an error: " + ex.getStatusCode().value() + "-"
					+ (ex.getResponseBodyAsString() != null ? ex.getResponseBodyAsString().replaceAll("\\n", "")
							: null);
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
		} catch (Exception ex) {
			// extract error
			String errorMessage = "TcrmOrganizationParty API throws an exception: " + ex.getMessage() != null
					? ex.getMessage().replaceAll("\\n", "")
					: null;
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
		}
	}
}
