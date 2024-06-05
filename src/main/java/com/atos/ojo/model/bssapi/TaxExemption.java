package com.atos.ojo.model.bssapi;

public class TaxExemption {
	String sequenceNumber;
	String type ;
	Long code;
	Double rate;
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "TaxExemption [sequenceNumber=" + sequenceNumber + ", type=" + type + ", code=" + code + ", rate=" + rate
				+ "]";
	}
}
