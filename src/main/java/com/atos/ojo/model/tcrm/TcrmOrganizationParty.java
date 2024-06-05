package com.atos.ojo.model.tcrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TcrmOrganizationParty {
    @JsonProperty("Identification")
	private Identification identification;
	private String email;
	private String marketType;
	private String mobilePhone;
	private String organizationName;
	private String parentAccountId;
	private String status;
}