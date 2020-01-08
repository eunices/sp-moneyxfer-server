package com.moneytransfer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUser {

	@JsonProperty("name")
	public String Name;
	
	@JsonProperty("email")
	public String Email;

	@JsonProperty("mobile")
	public String Mobile;
	
	@JsonProperty("password")
	public String Password;

}
