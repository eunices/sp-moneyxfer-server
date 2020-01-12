package com.moneytransfer.resources.v2;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.moneytransfer.models.v2.GetUserModel;
import com.moneytransfer.models.GetUserContactsModel;
import com.moneytransfer.models.GetUserProfileModel;
import com.moneytransfer.models.GetUserTransactionsModel;
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
		linkTransactions.Url = "v2/users/" + id + "/transactions";
		linkTransactions.Method = "GET";
		user.Links.add(linkTransactions);

		// Add contact links
		Link linkContacts = new Link();
		linkContacts.Label = "Contacts";
		linkContacts.Url = "v2/users/" + id + "/contacts";
		linkContacts.Method = "GET";
		user.Links.add(linkContacts);
		
		return Response.ok(user).build();
	}
	
	@GET
	@Path("{id}/transactions")
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response GetUserTransactions(@PathParam("id") int id, 
										@Context UriInfo info) {
		
		int page;
		int pageSize;
		
		try {
			page = Integer.parseInt(info.getQueryParameters().getFirst("page"));
		} catch (Exception e) {
			page = 1;
		}
		
		try {
			pageSize = Integer.parseInt(info.getQueryParameters().getFirst("pageSize"));
		} catch (Exception e) {
			pageSize = 10;
		}
	    
	    
		GetUserTransactionsModel user = manager.GetUserTransactions(id, page, pageSize);
		return Response.ok(user).build();
	}
	
	@GET
	@Path("{id}/contacts")
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response GetUserContacts(@PathParam("id") int id) {
		GetUserContactsModel user = manager.GetUserContacts(id);
		return Response.ok(user).build();
	}

}
