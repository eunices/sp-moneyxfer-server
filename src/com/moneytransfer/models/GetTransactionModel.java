package com.moneytransfer.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionModel { // v1

	@JsonProperty("amount")
	public double Amount;
	
	@JsonProperty("bankAccount")
	public String BankAccount;

	@JsonProperty("senderId")
	public String SenderId;
	
	@JsonProperty("recipientId")
	public String RecipientId;
	
	@JsonProperty("transactionDate")
	public Date TransactionDate;

}
