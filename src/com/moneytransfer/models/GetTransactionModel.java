package com.moneytransfer.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionModel { // v1

	@JsonProperty("amount")
	public double Amount;
	
	@JsonProperty("bankAccount")
	public String BankAccount;

	@JsonProperty("senderId")
	public int SenderId;
	
	@JsonProperty("recipientId")
	public int RecipientId;
	
	@JsonProperty("transactionDate")
	public String TransactionDate;

}
