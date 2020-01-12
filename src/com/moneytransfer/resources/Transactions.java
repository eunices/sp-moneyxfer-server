package com.moneytransfer.resources;

import com.moneytransfer.models.AddTransactionModel;
import com.moneytransfer.models.GetTransactionModel;
import com.moneytransfer.models.UpdateTransactionModel;
import com.moneytransfer.services.TransactionManager;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("transactions")
public class Transactions {
	
	private TransactionManager txtManager = new TransactionManager();
		
	@POST
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response Add(AddTransactionModel transaction) {
		String result = txtManager.Add(transaction);
		return Response.ok(result).build();
	}

	// TODO: nest under user/ ?
	@PUT
	@Path("{id}")
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response Update(UpdateTransactionModel transaction, @PathParam("id") int id) {
		String result = txtManager.Update(transaction, id);
		return Response.ok(result).build();
	}
	
}
