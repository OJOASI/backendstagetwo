package com.atos.ojo.model.batch;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BatchItem {

	@Id
	Integer itemId;
	Integer batchId;
	Integer crmId;
	String msisdn;
	String coCode;
	Date entryDate;
	Date updateDate;
	String status;
	String errorMsg;
	String itemUiData;
	Clob poData;
	Clob enrichData;
}
