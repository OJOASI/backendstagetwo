package com.atos.ojo.model.tcrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateOrganizationPartyResult {
	@JsonProperty("Errors")
	public String errors;

	@JsonProperty("IsSuccess")
	public Boolean isSuccess;

	@JsonProperty("Message")
	public String message;

	@JsonProperty("Id")
	public String id;

	@JsonProperty("TcrmId")
	public String tcrmId;

}