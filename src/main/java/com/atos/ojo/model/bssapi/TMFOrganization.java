package com.atos.ojo.model.bssapi;

import java.util.List;

public class TMFOrganization {
	String id;
	ExternalReference externalReference;
//	String placeOfBirth;
//	String isLegalEntity;
//	String type;
	String status;
	String creationDate;
	String ratePlanOtherCreditAndCharge;
	List<AdditionnalInfo> additionnalInfo;
	String role;
	List<TMFCharacteristic> characteristic;
	List<TMFContactMedium> contactMedium;
	TMFOrganizationIdentification organizationIdentification;
	List<PaymentMethod> paymentMethod;
	List<TaxExemption> taxExemption;
	List<TMFNBillingAccount> billingAccount;
	TMFOrganisationParentRelationship organisationParentRelationship;
	String tradingName;
	String priceGroupName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getPlaceOfBirth() {
//		return placeOfBirth;
//	}
//	public void setPlaceOfBirth(String placeOfBirth) {
//		this.placeOfBirth = placeOfBirth;
//	}
//	public String getIsLegalEntity() {
//		return isLegalEntity;
//	}
//	public void setIsLegalEntity(String isLegalEntity) {
//		this.isLegalEntity = isLegalEntity;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getRatePlanOtherCreditAndCharge() {
		return ratePlanOtherCreditAndCharge;
	}

	public void setRatePlanOtherCreditAndCharge(String ratePlanOtherCreditAndCharge) {
		this.ratePlanOtherCreditAndCharge = ratePlanOtherCreditAndCharge;
	}

	public List<AdditionnalInfo> getAdditionnalInfo() {
		return additionnalInfo;
	}

	public void setAdditionnalInfo(List<AdditionnalInfo> additionnalInfo) {
		this.additionnalInfo = additionnalInfo;
	}

	public List<TMFCharacteristic> getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(List<TMFCharacteristic> characteristic) {
		this.characteristic = characteristic;
	}

	public List<TMFContactMedium> getContactMedium() {
		return contactMedium;
	}

	public void setContactMedium(List<TMFContactMedium> contactMedium) {
		this.contactMedium = contactMedium;
	}

	public TMFOrganizationIdentification getOrganizationIdentification() {
		return organizationIdentification;
	}

	public void setOrganizationIdentification(TMFOrganizationIdentification organizationIdentification) {
		this.organizationIdentification = organizationIdentification;
	}

	public List<TaxExemption> getTaxExemption() {
		return taxExemption;
	}

	public void setTaxExemption(List<TaxExemption> taxExemption) {
		this.taxExemption = taxExemption;
	}

	public TMFOrganisationParentRelationship getOrganisationParentRelationship() {
		return organisationParentRelationship;
	}

	public void setOrganisationParentRelationship(TMFOrganisationParentRelationship organisationParentRelationship) {
		this.organisationParentRelationship = organisationParentRelationship;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public ExternalReference getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(ExternalReference externalReference) {
		this.externalReference = externalReference;
	}

	public String getPriceGroupName() {
		return priceGroupName;
	}

	public void setPriceGroupName(String priceGroupName) {
		this.priceGroupName = priceGroupName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPaymentMethod(List<PaymentMethod> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<PaymentMethod> getPaymentMethod() {
		return paymentMethod;
	}

	public List<TMFNBillingAccount> getBillingAccount() {
		return billingAccount;
	}

	public void setBillingAccount(List<TMFNBillingAccount> billingAccount) {
		this.billingAccount = billingAccount;
	}

	@Override
	public String toString() {
		return "TMFOrganization [id=" + id + ", externalReference=" + externalReference + ", status=" + status
				+ ", creationDate=" + creationDate + ", ratePlanOtherCreditAndCharge=" + ratePlanOtherCreditAndCharge
				+ ", additionnalInfo=" + additionnalInfo + ", role=" + role + ", contactMedium=" + contactMedium
				+ ", organizationIdentification=" + organizationIdentification + ", paymentMethod=" + paymentMethod
				+ ", taxExemption=" + taxExemption + ", billingAccount=" + billingAccount
				+ ", organisationParentRelationship=" + organisationParentRelationship + ", tradingName=" + tradingName
				+ ", priceGroupName=" + priceGroupName + "]";
	}

}
