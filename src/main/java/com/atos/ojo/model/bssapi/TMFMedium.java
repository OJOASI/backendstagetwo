package com.atos.ojo.model.bssapi;

public class TMFMedium {
	String city;
	String country;
//	String stateOrProvince;
	String streetNumber;
	String street1;
//	String street2;
	String emailAddress;
	String type;
	String number;
//	String circle;
	String postbox;
//	String quarter;
	String addressType;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
//	public String getStateOrProvince() {
//		return stateOrProvince;
//	}
//	public void setStateOrProvince(String stateOrProvince) {
//		this.stateOrProvince = stateOrProvince;
//	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
//	public String getStreet2() {
//		return street2;
//	}
//	public void setStreet2(String street2) {
//		this.street2 = street2;
//	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
//	public String getCircle() {
//		return circle;
//	}
//	public void setCircle(String circle) {
//		this.circle = circle;
//	}
	public String getPostbox() {
		return postbox;
	}
	public void setPostbox(String postbox) {
		this.postbox = postbox;
	}
//	public String getQuarter() {
//		return quarter;
//	}
//	public void setQuarter(String quarter) {
//		this.quarter = quarter;
//	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	@Override
	public String toString() {
		return "TMFMedium [city=" + city + ", country=" + country + ", streetNumber=" + streetNumber + ", street1="
				+ street1 + ", emailAddress=" + emailAddress + ", type=" + type + ", number=" + number + ", postbox="
				+ postbox + ", addressType=" + addressType + "]";
	}
	
	
	
}
