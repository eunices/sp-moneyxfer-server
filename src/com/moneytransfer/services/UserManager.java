package com.moneytransfer.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.moneytransfer.models.CreateUser;
import com.moneytransfer.db.ConnectionManager;


public class UserManager {
	
	public Boolean IsEmailUsed(CreateUser user) {
		Boolean isEmailUsed = false;
		
		try {
			String sql = "SELECT * FROM users " +
					"WHERE email = ?";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.Email);
			
			ResultSet rs = stmt.executeQuery();
			isEmailUsed = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isEmailUsed;
	}
	
	public Boolean IsUserValid(String email, String password) {
		Boolean isValid = false;
		
		try {
			
			// email is also username
			String sql = "SELECT * FROM users " +
					"WHERE email = ? " +
					"AND password = ?";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			isValid = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	public int CreateUser(CreateUser user) {
		
		int createdUser = 0;
		
		// DB calls
		
		// get connection
		try {
			
			/*String sql = "INSERT INTO users (name, email, phone) VALUES " + 
					"('John GF2', 'GF2@test.com', '999999999')";
			Connection conn = ConnectionManager.Get();
			Statement stmt = conn.createStatement();
			stmt.execute(sql);*/
						
			String sql = "INSERT INTO users (name, email, phone, password) VALUES " + 
					"(?, ?, ?, ?)";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, user.Name);
			stmt.setString(2, user.Email);
			stmt.setString(3, user.Mobile);
			stmt.setString(4, user.Password);
			stmt.execute();
			
			ResultSet idResult = stmt.getGeneratedKeys();
			if (idResult.next()){
				createdUser = idResult.getInt(1); // first user as it is "set"
			}
			
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return createdUser;
		
	}
	

}
