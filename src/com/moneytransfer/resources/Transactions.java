package com.moneytransfer.resources;

import com.moneytransfer.models.AddTransaction;
import com.moneytransfer.models.GetTransactionModel;
import com.moneytransfer.services.TransactionManager;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("transactions")
public class Transactions {
	
	private TransactionManager txtManager = new TransactionManager();
	
	@GET
	@RolesAllowed("user")
	@Produces("application/json")
	public Response GetAll() {
		GetTransactionModel tx = new GetTransactionModel();
		return Response.ok(tx).build();
	}
	
	@POST
	@RolesAllowed("user")
	@Consumes("application/json")
	public Response Add(AddTransaction transaction) {
		String result = txtManager.Add(transaction);
		return Response.ok(result).build();
	}

}
