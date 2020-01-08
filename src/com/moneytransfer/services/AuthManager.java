package com.moneytransfer.services;import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.moneytransfer.auth.jwt.JWTToken;
import com.moneytransfer.auth.jwt.jwtIssuer;
import com.moneytransfer.db.ConnectionManager;

public class AuthManager {
	
	private UserManager userManager = new UserManager();
	
	public Boolean isTokenValid(String token) {
		Boolean isValid = false;
		
		try {
			
			String sql = "SELECT * FROM auth WHERE token = ?";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, token);
			ResultSet rs = stmt.executeQuery();
			isValid = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
	public String Authenticate(String email, String password) {
		String token = "";
		Boolean isUserValid = userManager.IsUserValid(email, password);
		
		if (isUserValid) {
			// generate token, save to db, return it
			
			try {
				
				// Generate a UUID, to be saved as token
				String uuid = UUID.randomUUID().toString();
				
				// Create connection
				String sql = "INSERT INTO auth (token, email) " + 
						"VALUES (?, ?)";
				Connection conn = ConnectionManager.Get();				
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, uuid);
				stmt.setString(2, email);
				stmt.execute();
				
				token = uuid;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return token;
		
	}
	
	public String AuthenticateGetJWT(String email, String password) {
		String token = "";
		Boolean isUserValid = userManager.IsUserValid(email, password);
		
		if (isUserValid) {
			try {
				token = JWTToken.createJWT(jwtIssuer.getId(),
						jwtIssuer.getIssuer(), 
						"auth", "", "user", email, 60000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return token;
	}

}
