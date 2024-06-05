package com.atos.ojo.model.bssapi;

import java.util.Date;

public class TMFOrganizationIdentification {
	String type;
	String identificationId;
//	Date issuingDate;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdentificationId() {
		return identificationId;
	}
	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}
//	public Date getIssuingDate() {
//		return issuingDate;
//	}
//	public void setIssuingDate(Date issuingDate) {
//		this.issuingDate = issuingDate;
//	}
	@Override
	public String toString() {
		return "TMFOrganizationIdentification [type=" + type + ", identificationId=" + identificationId
				+  "]";
	}
}
