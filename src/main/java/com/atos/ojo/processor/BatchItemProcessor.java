package com.atos.ojo.processor;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.sql.rowset.serial.SerialClob;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.ojo.model.batch.BatchItem;
import com.atos.ojo.model.lizard.BillingAccountRef;
import com.atos.ojo.model.lizard.ProductOrderCreate;
import com.atos.ojo.model.lizard.ProductOrderItem;
import com.atos.ojo.model.lizard.RelatedParty;
import com.atos.ojo.service.BscsService;
import com.atos.ojo.service.LizardService;
import com.atos.ojo.service.TcrmService;
import com.atos.ojo.util.BatchUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BatchItemProcessor implements ItemProcessor<BatchItem, BatchItem> {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	BatchUtil batchUtil;

	@Autowired
	LizardService lizardService;

	@Autowired
	BscsService bscsService;

	@Autowired
	TcrmService tcrmService;

	@Override
	public BatchItem process(BatchItem item) throws IOException {

		Thread currentThread = Thread.currentThread();
		log.info("==> Processing Batch=" + item.getBatchId() + ",Item=" + item.getItemId() + ",Thread="
				+ currentThread.getName() + " Started.");

		ProductOrderCreate productOrderCreate = parsePoData(item);
		if (productOrderCreate != null && productOrderCreate.getDescription() != null
				&& !productOrderCreate.getDescription().isEmpty()) {
			String useCase = productOrderCreate.getDescription();
			switch (useCase) {
			// Service management scenarios
			case "MODIFICATION":
				lizardService.callLizardProductOrderApi(productOrderCreate, null, item);
				break;

			// Contract management scenario.
			case "REACTIVATION":
			case "TERMINATION":
			case "HARDSUSPENSION":
			case "SOFTSUSPENSION":
				lizardService.callLizardProductOrderApi(productOrderCreate, null, item);
				break;

			// Rateplan migration
			case "MIGRATION":
				lizardService.callLizardProductOrderApi(productOrderCreate, null, item);
				break;

			// acquisition scenario
			case "ACQUISITION":
				// get enrich data
				Map<String, String> enrichMap = parseEnrichData(item);

				if (enrichMap != null) {

					// TcrmId validation
					if (!enrichMap.containsKey("TcrmId")) {
						tcrmService.callCreateOrganizationParty(enrichMap, item);
					} else {
						log.info("TCRM API call bypassed - TcrmId already enriched");
					}
					// billingAccountId validation
					if (enrichMap.containsKey("TcrmId") && !enrichMap.containsKey("billingAccountId")) {
						bscsService.callPartyManagement(enrichMap, item,1);
					} else {
						log.info("BSS API call bypassed - Missing TcrmId or billingAccountId already present.");
					}
					// Lizard validation
					if (enrichMap.containsKey("TcrmId") && enrichMap.containsKey("billingAccountId")
							&& !enrichMap.containsKey("productOrderId")) {
						enrichProductOrder(productOrderCreate, enrichMap, item);
						lizardService.callLizardProductOrderApi(productOrderCreate, enrichMap, item);
					} else {
						log.info(
								"Lizard API call bypassed - either any of TcrmId or billingAccountId is missing, or the billingAccountId already exists.");
					}
				} else {
					String errorMessage = "Enrichment data missing! unable to process acquisition request. Please review Batch entry.";
					log.error(errorMessage);
					batchUtil.setItemErrorAttributes(item, errorMessage);
				}
				break;

			}

		}
		log.info("<== Processing Batch=" + item.getBatchId() + ",Item=" + item.getItemId() + ",Thread="
				+ currentThread.getName() + " Done.");

		return item;
	}

	// enrich product order create object
	private void enrichProductOrder(ProductOrderCreate productOrderCreate, Map<String, String> enrichMap,
			BatchItem item) {
		// set billing account
		BillingAccountRef billingAccountRef = new BillingAccountRef();
		billingAccountRef.setName("BillingAccount");
		billingAccountRef.setId(enrichMap.get("billingAccountId"));
		productOrderCreate.setBillingAccount(billingAccountRef);

		// Set related Party
		boolean customerExists = productOrderCreate.getRelatedParty().stream()
				.anyMatch(rp -> "customer".equals(rp.getName()));

		if (!customerExists) {
			RelatedParty relatedParty = new RelatedParty();
			relatedParty.setId(enrichMap.get("TcrmId"));
			relatedParty.setName("customer");
			productOrderCreate.getRelatedParty().add(relatedParty);
		}

		List<ProductOrderItem> productOrderItems = productOrderCreate.getProductOrderItem();
		Optional<ProductOrderItem> mainProductOrderItemOpt = productOrderItems.stream()
				.filter(productOrderItem -> "main".equals(productOrderItem.getType())).findFirst();
		if (mainProductOrderItemOpt.isPresent()) {
			Pattern pattern = Pattern.compile("FN.*Customer.*Code.*");
			ProductOrderItem mainProductOrderItem = mainProductOrderItemOpt.get();
			mainProductOrderItem.getProduct().getProductCharacteristic().stream()
					.filter(pc -> pattern.matcher(pc.getName()).matches()).findFirst()
					.ifPresent(pc -> pc.setValue(enrichMap.get("custcode")));

		} else {
			log.warn("CustCode characteristic not available in main offer. Proceeding without populating it.");
		}

		// item update
		Clob poDataClob;
		try {
			poDataClob = new SerialClob(objectMapper.writeValueAsString(productOrderCreate).toString().toCharArray());
			item.setPoData(poDataClob);
		} catch (JsonProcessingException | SQLException e) {
			log.warn("Error converting POData to string. Resuming without updating database - BatchId="
					+ item.getBatchId() + " ,ItemId=" + item.getItemId());
			e.printStackTrace();
		}
	}

	// Parse Product Order data
	private ProductOrderCreate parsePoData(BatchItem item) {
		try {
			ProductOrderCreate productOrderCreate = objectMapper.readValue(batchUtil.clobToString(item.getPoData()),
					ProductOrderCreate.class);
			if (productOrderCreate == null)
				throw new Exception("Item PO Date is null");
			else if (!isValidProductOrderObject(productOrderCreate))
				throw new Exception("Invalid ProductOrderCreate Object");
			else
				return productOrderCreate;

		} catch (Exception ex) {
			String errorMessage = "Parsing item PO data failed - Item skipped: "
					+ (ex.getMessage() != null ? ex.getMessage().replaceAll("\\n", "") : null);
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
			return null;
		}
	}

	// Parse Enrich Data
	private Map<String, String> parseEnrichData(BatchItem item) {

		try {
			Map<String, String> enrichMap = objectMapper.readValue(batchUtil.clobToString(item.getEnrichData()),
					new TypeReference<Map<String, String>>() {
					});
			if (enrichMap == null || enrichMap.isEmpty())
				throw new Exception("Enrich map is null or empty");
			else if (!isCompleteEnrichMap(enrichMap))
				throw new Exception("Incomplete  Enrich Map");
			else
				return enrichMap;

		} catch (Exception ex) {
			String errorMessage = "Parsing item Enrich data failed - Item skipped: "
					+ (ex.getMessage() != null ? ex.getMessage().replaceAll("\\n", "") : null);
			log.error(errorMessage);
			batchUtil.setItemErrorAttributes(item, errorMessage);
			ex.printStackTrace();
			return null;
		}

	}

	private boolean isCompleteEnrichMap(Map<String, String> enrichMap) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidProductOrderObject(ProductOrderCreate productOrderCreate) {
		return true;
	}
}