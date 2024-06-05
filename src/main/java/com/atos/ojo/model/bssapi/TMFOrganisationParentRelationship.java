package com.atos.ojo.model.bssapi;

public class TMFOrganisationParentRelationship {
	String id;
	ExternalReference externalReference;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ExternalReference getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(ExternalReference externalReference) {
		this.externalReference = externalReference;
	}
	@Override
	public String toString() {
		return "TMFOrganisationParentRelationship [id=" + id + ", externalReference=" + externalReference + "]";
	}
}
