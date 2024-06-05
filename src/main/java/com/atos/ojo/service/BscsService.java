package com.atos.ojo.service;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
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
import com.atos.ojo.model.bssapi.AdditionnalInfo;
import com.atos.ojo.model.bssapi.ExternalReference;
import com.atos.ojo.model.bssapi.PaymentMethod;
import com.atos.ojo.model.bssapi.TMFContactMedium;
import com.atos.ojo.model.bssapi.TMFMedium;
import com.atos.ojo.model.bssapi.TMFNBillingAccount;
import com.atos.ojo.model.bssapi.TMFOrganisationParentRelationship;
import com.atos.ojo.model.bssapi.TMFOrganization;
import com.atos.ojo.model.bssapi.TMFOrganizationIdentification;
import com.atos.ojo.util.BatchUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BscsService {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	BatchUtil batchUtil;

	@Value("${bss.party.url}")
	private String bssPartyUrl;
	
	@Value("${stagetwo.batchitem.status.inprogress}")
	private String inProgress;

	public void callPartyManagement(Map<String, String> enrichMap, BatchItem item) {

		// Initialize request parameters
		String ratePlanOtherCreditAndCharge = enrichMap.get("ratePlanOtherCreditAndCharge");
		String tradingName = enrichMap.get("tradingName");
		String tcrmId = enrichMap.get("TcrmId");

		// billing account info
		String payementResponsible = enrichMap.get("payementResponsible");
		String billingAccountId = enrichMap.get("respBillingAccountId");

		// contact medium details
		String addressRole = "BillAddressRole";
		String addressType = "Consumer";
		String type = "PostalAddress";
		String city = enrichMap.get("city");
		// String state = enrichMap.get("state");
		String country = enrichMap.get("country");
		String number = enrichMap.get("number");
		String street1 = enrichMap.get("street1");
		// String PostalAddress = enrichMap.get("PostalAddress");

		// organisation Parent Relationship
		String bscsParentCustcode = enrichMap.get("bscsParentCustcode");

		//
		String role = "subscriber";
		String status = "Validated";

		// organizationIdentification
		String identType = "companyRegistrationNumber";
		String identificationId = enrichMap.get("companyRegistrationNumber");

		// payment method
		String bankName = enrichMap.get("bankName");
		String bankCity = enrichMap.get("bankCity");
		String bankZip = enrichMap.get("bankZip");
		String idPayementType = enrichMap.get("idPayementType");
		String isPayementArrangement = "true";

		// additionnalInfo
		String text01Value = enrichMap.get("TEXT01");
		String text02Value = enrichMap.get("TEXT02");
		String text03Value = enrichMap.get("TEXT03");
		String text25Value = enrichMap.get("TEXT25");

		// Build request object
		TMFOrganization request = new TMFOrganization();
		ExternalReference externalReference = new ExternalReference ();
		externalReference.setExternalId(tcrmId);
		externalReference.setExternalIdKey("1");
		request.setExternalReference(externalReference );
		
		request.setRatePlanOtherCreditAndCharge(ratePlanOtherCreditAndCharge);
		request.setTradingName(tradingName);

		// billing account
		List<TMFNBillingAccount> billingAccount = new ArrayList<>();
		TMFNBillingAccount tMFNBillingAccount = new TMFNBillingAccount();
		tMFNBillingAccount.setPayementResponsible(payementResponsible);
		billingAccount.add(tMFNBillingAccount);
		request.setBillingAccount(billingAccount);

		// Contact Medium
		List<TMFContactMedium> tMFContactMediums = new ArrayList<>();

		TMFContactMedium tMFContactMedium = new TMFContactMedium();
		tMFContactMedium.setAddressRole(addressRole);

		List<TMFMedium> mediums = new ArrayList<>();
		TMFMedium medium = new TMFMedium();
		medium.setAddressType(addressType);
		medium.setType(type);
		medium.setCity(city);
		medium.setCountry(country);
		medium.setNumber(number);
		medium.setStreet1(street1);

		mediums.add(medium);
		tMFContactMedium.setMedium(mediums);
		tMFContactMediums.add(tMFContactMedium);

		request.setContactMedium(tMFContactMediums);

		// Organisation relationShip
		TMFOrganisationParentRelationship tMFOrganisationParentRelationship = new TMFOrganisationParentRelationship();
		tMFOrganisationParentRelationship.setId(bscsParentCustcode);
		ExternalReference pexternalReference = new ExternalReference();
		pexternalReference.setExternalId("");
		pexternalReference.setExternalIdKey("");
		tMFOrganisationParentRelationship.setExternalReference(pexternalReference);

		request.setOrganisationParentRelationship(tMFOrganisationParentRelationship);

		// Trading name
		request.setRole(role);
		request.setStatus(status);

		// ORganization identification
		TMFOrganizationIdentification organizationIdentification = new TMFOrganizationIdentification();
		organizationIdentification.setType(identType);
		organizationIdentification.setIdentificationId(identificationId);
		request.setOrganizationIdentification(organizationIdentification);

		// Payment method
		List<PaymentMethod> paymentMethods = new ArrayList<>();
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setBankName(bankName);
		paymentMethod.setBankCity(bankCity);
		paymentMethod.setBankZip(bankZip);
		paymentMethod.setIdPayementType(idPayementType);
		paymentMethod.setIsPayementArrangement(isPayementArrangement);

		paymentMethods.add(paymentMethod);
		request.setPaymentMethod(paymentMethods);

		// Additional info
		List<AdditionnalInfo> additionnalInfo = new ArrayList<>();

		AdditionnalInfo text01 = new AdditionnalInfo();
		text01.setKey("TEXT01");
		text01.setValue(text01Value);
		additionnalInfo.add(text01);

		AdditionnalInfo text02 = new AdditionnalInfo();
		text01.setKey("TEXT02");
		text01.setValue(text02Value);
		additionnalInfo.add(text02);

		AdditionnalInfo text03 = new AdditionnalInfo();
		text01.setKey("TEXT03");
		text01.setValue(text03Value);
		additionnalInfo.add(text03);

		AdditionnalInfo text25 = new AdditionnalInfo();
		text01.setKey("TEXT25");
		text01.setValue(text25Value);
		additionnalInfo.add(text25);

		request.setAdditionnalInfo(additionnalInfo);

		// Call BSS API - Create party
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<TMFOrganization> requestEntity = new HttpEntity<>(request, headers);
			log.info("--> BSS Party API Request: {} {} | Body: {}", HttpMethod.POST, bssPartyUrl,
					objectMapper.writeValueAsString(request));

			ResponseEntity<TMFOrganization> response = restTemplate.exchange(bssPartyUrl, HttpMethod.POST,
					requestEntity, TMFOrganization.class);
			log.info("<-- BSS Party API Response: Status: {} | Body: {}", response.getStatusCode(),
					objectMapper.writeValueAsString(response.getBody()));

			// extract custcode.
			String custcode;
			if (response.getBody() != null) {
				custcode=response.getBody().getId();
			}
			else {
				String errorMessage = "Invalid BSS API response: Missing CustCode. Please verify.";
				log.error(errorMessage);
				batchUtil.setItemErrorAttributes(item, errorMessage);
				return;
			}

			// get billing account id in case of payment resp customer & cust code
			if (payementResponsible.equals("true")) {
				if (response.getBody() != null && response.getBody().getBillingAccount() != null
						&& !response.getBody().getBillingAccount().isEmpty()
						&& response.getBody().getBillingAccount().get(0) != null
						&& response.getBody().getBillingAccount().get(0).getId() != null
						&& !response.getBody().getBillingAccount().get(0).getId().isEmpty()) {

					billingAccountId = response.getBody().getBillingAccount().get(0).getId();
				} else {
					String errorMessage = "Invalid BSS API response: Empty body, billing account list, or first billing account ID. Please verify.";
					log.error(errorMessage);
					batchUtil.setItemErrorAttributes(item, errorMessage);
					return;
				}
			}

			// set the billing account in the map.
			enrichMap.put("billingAccountId", billingAccountId);
			enrichMap.put("custcode", custcode);

			Clob enrichDataClob = new SerialClob(objectMapper.writeValueAsString(enrichMap).toString().toCharArray());
			item.setEnrichData(enrichDataClob);
			item.setStatus(inProgress);
			item.setErrorMsg("BSCS Party Successfully Created");
		} catch (HttpClientErrorException |

				HttpServerErrorException ex) {
			// extract error
			String errorMessage = "BSS API returned an error: " + ex.getStatusCode().value() + "-"
					+ (ex.getResponseBodyAsString() != null ? ex.getResponseBodyAsString().replaceAll("\\n", "")
							: null);
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
		} catch (Exception ex) {
			String errorMessage = "BSS API throws an exception: "
					+ (ex.getMessage() != null ? ex.getMessage().replaceAll("\\n", "") : "");

			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
		}
	}

}
