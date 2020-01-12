package com.moneytransfer.models;


import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "CreateUser")
public class CreateUserModel {

	@JsonProperty("name")
	public String Name;
	
	@JsonProperty("email")
	public String Email;

	@JsonProperty("mobile")
	public String Mobile;
	
	@JsonProperty("password")
	public String Password;

}
