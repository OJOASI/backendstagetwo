package com.atos.ojo.model.tcrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TcrmOrganizationPartyResponse {
	
	@JsonProperty("CreateOrganizationPartyResult")
	 CreateOrganizationPartyResult CreateOrganizationPartyResult;

}