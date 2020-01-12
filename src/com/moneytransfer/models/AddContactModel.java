package com.moneytransfer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddContactModel {

	@JsonProperty("alias")
	public String Alias;

	@JsonProperty("contactId")
	public int ContactId;
	
}
