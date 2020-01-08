package com.moneytransfer.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moneytransfer.db.ConnectionManager;
import com.moneytransfer.models.AddTransaction;

public class TransactionManager {
	
	UserManager user = new UserManager();
	
	public String Add(AddTransaction transaction) {
		String result = "Transaction is not complete.";
		
		// returns 0 if not found
		int senderId = GetUserId(transaction.SenderId); 
		int recipientId = GetUserId(transaction.RecipientId);
		
		// TODO: how to check that user logged in is the sender?
		// is it a front-end thing?
		
		if (senderId != 0 & recipientId != 0) {
			try {
				
				String sql = "INSERT INTO transactions " +
						"(amount, bank_account, sender_id, recipient_id) " + 
						"VALUES (?, ?, ?, ?)";
				Connection conn = ConnectionManager.Get();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setDouble(1, transaction.Amount);
				stmt.setString(2, transaction.BankAccount);
				stmt.setInt(3, senderId);
				stmt.setInt(4, recipientId);
				
				stmt.execute();
				
				result = "You have successfully added the transaction";
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "Either the recipient or sender does not exist.";
		}

		return result;
	}
	
	public int GetUserId(String email) {
		int userId = 0;
		
		try {
			String sql = "SELECT id FROM users " +
					"WHERE email = ?";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet idResult = stmt.executeQuery();		
			if (idResult.next()){
				userId = idResult.getInt(1); // first user as it is "set"
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;	
	}	
}
