package com.moneytransfer.models;

import java.util.ArrayList;

public class GetUserTransactionsModel {
	public ArrayList<GetTransactionModel> Transactions;

	public GetUserTransactionsModel() {
		this.Transactions = new ArrayList<GetTransactionModel>();
	}

}
