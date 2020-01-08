package com.moneytransfer.models.v2;

import java.util.ArrayList;

import com.moneytransfer.models.GetUserProfileModel;
import com.moneytransfer.models.Link;

public class GetUserModel {
	public GetUserProfileModel Profile;
	public ArrayList<Link> Links;
	public GetUserModel() {
		Profile = new GetUserProfileModel();
		Links = new ArrayList();
	}
}