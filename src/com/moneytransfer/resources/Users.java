package com.moneytransfer.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.moneytransfer.models.CreateUser;
import com.moneytransfer.services.UserManager;

@Path("users")
public class Users {
	private UserManager manager = new UserManager();
	
	@POST
	@Consumes("application/json")
	public Response CreateUser(CreateUser user) {
		String result = null;
		
		try {
			if (!manager.IsEmailUsed(user)) {
				int id = manager.CreateUser(user);
				if (id > 0) {
					result = "Successfully created user";
				} else {
					result = "Unsuccessful account creation";
				}
			} else {
				result = "Email already exists, please use another email.";
			}
		} catch (Exception e) {
			result = "Unsuccessful account creation";
		}
		
		return Response.ok(result).build();
		
	}

}
