package com.atos.ojo.model.bssapi;

public class ExternalReference {
//	String href;
//	String type;
	String externalId;
	String externalIdKey;
//	public String getHref() {
//		return href;
//	}
//	public void setHref(String href) {
//		this.href = href;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getExternalIdKey() {
		return externalIdKey;
	}
	public void setExternalIdKey(String externalIdKey) {
		this.externalIdKey = externalIdKey;
	}
	@Override
	public String toString() {
		return "ExternalReference [externalId=" + externalId + ", externalIdKey=" + externalIdKey + "]";
	}
	
}
