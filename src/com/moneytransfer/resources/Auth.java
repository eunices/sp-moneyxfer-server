package com.moneytransfer.resources;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;   

import com.moneytransfer.services.AuthManager;

@Path("auth")
public class Auth {
	
	private AuthManager authManager = new AuthManager();
	
	@POST
	public Response Login(
			@FormParam("email") String email, 
			@FormParam("password") String password) {
		
		// auth manager to validate username (email) and password
		// String token = authManager.Authenticate(email, password); // basic
		String token = authManager.AuthenticateGetJWT(email, password);
		
		return Response.ok(token).build();
	}

}
