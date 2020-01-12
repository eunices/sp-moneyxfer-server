package com.moneytransfer.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "CreateUser")
public class CreateUserModel {

	@XmlAttribute(name = "Name")
	@JsonProperty("name")
	public String Name;

	@XmlAttribute(name = "Email")
	@JsonProperty("email")
	public String Email;

	@XmlAttribute(name = "Mobile")
	@JsonProperty("mobile")
	public String Mobile;
	
	@XmlAttribute(name = "Password")
	@JsonProperty("password")
	public String Password;

}
