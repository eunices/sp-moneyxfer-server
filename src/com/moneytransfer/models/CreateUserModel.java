package com.moneytransfer.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "CreateUser")
public class CreateUserModel {

	@JsonProperty("name")
	@XmlAttribute(name = "Name")
	public String Name;

	@JsonProperty("email")
	@XmlAttribute(name = "Email")
	public String Email;

	@JsonProperty("mobile")
	@XmlAttribute(name = "Mobile")
	public String Mobile;
	
	@JsonProperty("password")
	@XmlAttribute(name = "Password")
	public String Password;

}
