package com.moneytransfer.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.moneytransfer.db.ConnectionManager;
import com.moneytransfer.models.AddTransactionModel;
import com.moneytransfer.models.UpdateTransactionModel;

public class TransactionManager {
	
	UserManager user = new UserManager();
	
	public String Add(AddTransactionModel transaction) {
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
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				stmt.setDouble(1, transaction.Amount);
				stmt.setString(2, transaction.BankAccount);
				stmt.setInt(3, senderId);
				stmt.setInt(4, recipientId);
				
				int i = stmt.executeUpdate();
				if (i>0){
					result = "You have successfully added the transaction";		
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "Either the recipient or sender does not exist.";
		}

		return result;
	}
	
	public String Update(UpdateTransactionModel transaction, int id) {
		String result = "Transaction was not updated";
	
		try {
			
			String sql = "UPDATE transactions set " +
					"amount = ?, bank_account = ?, sender_id = ?, recipient_id = ? " +
					"WHERE id = ?";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, transaction.Amount);
			stmt.setString(2, transaction.BankAccount);
			stmt.setString(3, transaction.SenderId);
			stmt.setString(4, transaction.RecipientId);
			stmt.setInt(5, id);
			
			int i = stmt.executeUpdate();
			if (i>0){
				result = "You have successfully updated the transaction";
			}


		} catch (Exception e) {
			e.printStackTrace();	
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
