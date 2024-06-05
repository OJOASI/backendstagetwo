package com.atos.ojo.model.bssapi;

import java.util.ArrayList;
import java.util.List;

public class TMFNBillingAccount {
	String billCycle;
	String payementResponsible;
	String id;
//	String termCode;
//	String glCode;
//	String billingAccountName;
//	String billingAccountCode;
//	String callDetails;
//	String primaryBillingAccount;
//	String invoiceIndicator;
//	String csIdPub;
//	String bmIdPub;
//	String currencyidPub;
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public String getPayementResponsible() {
		return payementResponsible;
	}
	public void setPayementResponsible(String payementResponsible) {
		this.payementResponsible = payementResponsible;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TMFNBillingAccount [billCycle=" + billCycle + ", payementResponsible=" + payementResponsible + ", id="
				+ id + "]";
	}
	
	

//	public String getTermCode() {
//		return termCode;
//	}
//	public void setTermCode(String termCode) {
//		this.termCode = termCode;
//	}
//	public String getGlCode() {
//		return glCode;
//	}
//	public void setGlCode(String glCode) {
//		this.glCode = glCode;
//	}
//	public String getBillingAccountName() {
//		return billingAccountName;
//	}
//	public void setBillingAccountName(String billingAccountName) {
//		this.billingAccountName = billingAccountName;
//	}
//	public String getBillingAccountCode() {
//		return billingAccountCode;
//	}
//	public void setBillingAccountCode(String billingAccountCode) {
//		this.billingAccountCode = billingAccountCode;
//	}
//	public String getCallDetails() {
//		return callDetails;
//	}
//	public void setCallDetails(String callDetails) {
//		this.callDetails = callDetails;
//	}
//	public String getPrimaryBillingAccount() {
//		return primaryBillingAccount;
//	}
//	public void setPrimaryBillingAccount(String primaryBillingAccount) {
//		this.primaryBillingAccount = primaryBillingAccount;
//	}
//	public String getInvoiceIndicator() {
//		return invoiceIndicator;
//	}
//	public void setInvoiceIndicator(String invoiceIndicator) {
//		this.invoiceIndicator = invoiceIndicator;
//	}
//	public String getCsIdPub() {
//		return csIdPub;
//	}
//	public void setCsIdPub(String csIdPub) {
//		this.csIdPub = csIdPub;
//	}
//	public String getBmIdPub() {
//		return bmIdPub;
//	}
//	public void setBmIdPub(String bmIdPub) {
//		this.bmIdPub = bmIdPub;
//	}
//	public String getCurrencyidPub() {
//		return currencyidPub;
//	}
//	public void setCurrencyidPub(String currencyidPub) {
//		this.currencyidPub = currencyidPub;
//	}
//	
//	@Override
//	public String toString() {
//		return "BillingAccount [billCycle=" + billCycle
//				+ ", payementResponsible=" 
//				+ payementResponsible 
//				+ " termCode "
//				+ termCode 
//				+ " glCode "
//				+ glCode
//				+ " billingAccountName "
//				+ billingAccountName
//				+" billingAccountCode "
//				+ billingAccountCode
//				+" callDetails "
//				+ callDetails
//				+ " primaryBillingAccount "
//				+ primaryBillingAccount
//				+ " invoiceIndicator "
//				+ invoiceIndicator
//				+ " csIdPub "
//				+ csIdPub
//				+ "bmIdPub"
//				+ bmIdPub 
//				+ "currencyidPub"
//				+ currencyidPub +" ]";
//	
//	}
//	

	

	
	
	

}
