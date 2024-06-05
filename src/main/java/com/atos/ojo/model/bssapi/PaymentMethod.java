package com.atos.ojo.model.bssapi;

public class PaymentMethod {
	String idPayementType;
	String codeAgencyCreditCard;
	String accountOwner;
	String creditCardValidateDate;
	String orderNumber;
	String accountNumber;
	String bankCode;
	String bankName;
	String bankStreet;
	String bankStreetNumber;
	String bankCity;
	String bankZip;
	String bankState;
	String bankCounty;
	String bankCountry;
	String internationnalBankIdentifier;
	String isPayementArrangement;
	String isUsed;
	public String getIdPayementType() {
		return idPayementType;
	}
	public void setIdPayementType(String idPayementType) {
		this.idPayementType = idPayementType;
	}
	public String getCodeAgencyCreditCard() {
		return codeAgencyCreditCard;
	}
	public void setCodeAgencyCreditCard(String codeAgencyCreditCard) {
		this.codeAgencyCreditCard = codeAgencyCreditCard;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public String getCreditCardValidateDate() {
		return creditCardValidateDate;
	}
	public void setCreditCardValidateDate(String creditCardValidateDate) {
		this.creditCardValidateDate = creditCardValidateDate;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankStreet() {
		return bankStreet;
	}
	public void setBankStreet(String bankStreet) {
		this.bankStreet = bankStreet;
	}
	public String getBankStreetNumber() {
		return bankStreetNumber;
	}
	public void setBankStreetNumber(String bankStreetNumber) {
		this.bankStreetNumber = bankStreetNumber;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankZip() {
		return bankZip;
	}
	public void setBankZip(String bankZip) {
		this.bankZip = bankZip;
	}
	public String getBankState() {
		return bankState;
	}
	public void setBankState(String bankState) {
		this.bankState = bankState;
	}
	public String getBankCounty() {
		return bankCounty;
	}
	public void setBankCounty(String bankCounty) {
		this.bankCounty = bankCounty;
	}
	public String getBankCountry() {
		return bankCountry;
	}
	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}
	public String getInternationnalBankIdentifier() {
		return internationnalBankIdentifier;
	}
	public void setInternationnalBankIdentifier(String internationnalBankIdentifier) {
		this.internationnalBankIdentifier = internationnalBankIdentifier;
	}
	public String getIsPayementArrangement() {
		return isPayementArrangement;
	}
	public void setIsPayementArrangement(String isPayementArrangement) {
		this.isPayementArrangement = isPayementArrangement;
	}
	
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "PaymentMethod [idPayementType=" + idPayementType + ", codeAgencyCreditCard=" + codeAgencyCreditCard
				+ ", accountOwner=" + accountOwner + ", creditCardValidateDate=" + creditCardValidateDate
				+ ", orderNumber=" + orderNumber + ", accountNumber=" + accountNumber + ", bankCode=" + bankCode
				+ ", bankName=" + bankName + ", bankStreet=" + bankStreet + ", bankStreetNumber=" + bankStreetNumber
				+ ", bankCity=" + bankCity + ", bankZip=" + bankZip + ", bankState=" + bankState + ", bankCounty="
				+ bankCounty + ", bankCountry=" + bankCountry + ", internationnalBankIdentifier="
				+ internationnalBankIdentifier + ", isPayementArrangement=" + isPayementArrangement + ", isUsed="
				+ isUsed + "]";
	}
}
