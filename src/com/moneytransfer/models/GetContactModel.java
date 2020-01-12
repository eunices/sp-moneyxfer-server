package com.moneytransfer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetContactModel { // v1
	
	@JsonProperty("alias")
	public String Alias; // nickname of contact
	
	@JsonProperty("userId")
	public int UserId; // id of user
	
	@JsonProperty("contactId")
	public int ContactId; // id of contacts

}
