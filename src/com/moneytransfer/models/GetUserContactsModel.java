package com.moneytransfer.models;

import java.util.ArrayList;

public class GetUserContactsModel {
	public ArrayList<GetContactModel> Contacts;
	
	public GetUserContactsModel() {
		this.Contacts = new ArrayList<GetContactModel>();
	}
}
