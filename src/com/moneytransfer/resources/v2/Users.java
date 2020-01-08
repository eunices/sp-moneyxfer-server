package com.moneytransfer.resources.v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.moneytransfer.models.v2.GetUserModel;
import com.moneytransfer.models.GetUserProfileModel;
import com.moneytransfer.models.Link;

import com.moneytransfer.services.UserManager;

@Path("v2/users")
public class Users extends com.moneytransfer.resources.Users {
	
	private UserManager manager = new UserManager();
	
	@Override
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response GetOne(@PathParam("id") int id) {

		GetUserModel user = new GetUserModel();
		
		// Add profile
		GetUserProfileModel profile = manager.GetUserProfile(id);
		user.Profile = profile;
		
		// Add transaction links
		Link linkTransactions = new Link();
		linkTransactions.Label = "Transactions";
		linkTransactions.Url = "/users/" + id + "/transactions";
		linkTransactions.Method = "GET";
		user.Links.add(linkTransactions);

		// Add contact links
		Link linkContacts = new Link();
		linkContacts.Label = "Contacts";
		linkContacts.Url = "/users/" + id + "/contacts";
		linkContacts.Method = "GET";
		user.Links.add(linkContacts);
		
		return Response.ok(user).build();
	}

}
