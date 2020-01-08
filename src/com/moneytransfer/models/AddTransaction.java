package com.moneytransfer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTransaction {

	@JsonProperty("amount")
	public double Amount;
	
	@JsonProperty("bankAccount")
	public String BankAccount;

	@JsonProperty("senderId")
	public String SenderId;
	
	@JsonProperty("recipientId")
	public String RecipientId;

}
