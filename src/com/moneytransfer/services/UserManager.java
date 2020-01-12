package com.moneytransfer.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.moneytransfer.models.AddContactModel;
import com.moneytransfer.models.CreateUserModel;
import com.moneytransfer.models.GetContactModel;
import com.moneytransfer.models.GetTransactionModel;
import com.moneytransfer.models.GetUserContactsModel;
import com.moneytransfer.models.GetUserProfileModel;
import com.moneytransfer.models.GetUserTransactionsModel;
import com.moneytransfer.models.GetUserModel;
import com.moneytransfer.db.ConnectionManager;


public class UserManager {
	
	public String DeleteContact(int userId, int contactId) {
		String result = "Contact was not deleted";
	
		try {
			
			String sql = "DELETE FROM `contacts` " +
					"WHERE user_id = ? AND contact_id = ?";
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			stmt.setInt(2, contactId);
			
			int i = stmt.executeUpdate();
			if (i>0){
				result = "You have successfully deleted the contact";		
			}

		} catch (Exception e) {
			e.printStackTrace();	
		}
				
		return result;
		}
	
	public String AddContact(AddContactModel contact, int id) {
		String result = "Contact was not added";
	
		try {
			
//			String sql = "INSERT INTO `contacts` (alias, user_id, contact_id) VALUES " +
//					"(?, ?, ?)";
			String sql = "INSERT INTO `contacts` (`alias` , `user_id`, `contact_id`) " +
					"SELECT * FROM (SELECT ?, ?, ?) AS tmp " +
					"WHERE NOT EXISTS "+
					"(SELECT `user_id` FROM `contacts` WHERE `user_id` = ? AND `contact_id` = ?) LIMIT 1"; 
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, contact.Alias);
			stmt.setInt(2, id);
			stmt.setInt(3, contact.ContactId);
			stmt.setInt(4, id);
			stmt.setInt(5, contact.ContactId);
			
			
			int i = stmt.executeUpdate();
			if (i>0){
				result = "You have successfully added the contact";		
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
				
		return result;
		}
	
	public GetUserTransactionsModel GetUserTransactions(int id, int page, int pageSize) {
		
		GetUserTransactionsModel transactions = new GetUserTransactionsModel();
		
		try {
			Connection conn = ConnectionManager.Get();
			String sql = "SELECT amount, bank_account, transaction_date, recipient_id, sender_id "+
					"FROM `transactions` " + 
					"WHERE recipient_id = ? OR sender_id = ? " + 
					"ORDER BY transaction_date " +
					"LIMIT ? OFFSET ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			stmt.setInt(3, pageSize);
			stmt.setInt(4, (page-1)*pageSize);

			
			ResultSet rsTransactions = stmt.executeQuery();
			while (rsTransactions.next()) {
				GetTransactionModel tx = new GetTransactionModel();
				tx.Amount = rsTransactions.getDouble("amount");
				tx.BankAccount = rsTransactions.getString("bank_account");
				tx.TransactionDate = rsTransactions.getDate("transaction_date");
				tx.RecipientId = rsTransactions.getInt("recipient_id");
				tx.SenderId = rsTransactions.getInt("sender_id");
				transactions.Transactions.add(tx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
	}

	public GetUserContactsModel GetUserContacts(int id) {
		
		GetUserContactsModel contacts = new GetUserContactsModel();
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT alias, user_id, contact_id FROM `contacts`" 
					+ " WHERE user_id = ?");
			stmt.setInt(1, id);
			ResultSet rsContacts = stmt.executeQuery();
			while (rsContacts.next()) {
				GetContactModel tx = new GetContactModel();
				tx.Alias = rsContacts.getString("alias");
				tx.UserId = rsContacts.getInt("user_id");
				tx.ContactId = rsContacts.getInt("contact_id");
				
				contacts.Contacts.add(tx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contacts;
	}

	
	public GetUserProfileModel GetUserProfile(int id) {
		// used in v2 (profile + links)	
		
		GetUserProfileModel profile = new GetUserProfileModel();
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT name, email, phone FROM users"
					+ " WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rsUser = stmt.executeQuery();
			if (rsUser.next()) {
				profile.Name = rsUser.getString("name");
				profile.Email = rsUser.getString("email");
				profile.Phone = rsUser.getString("phone");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profile;
	}
	
	public GetUserModel GetUser(int id) {
		
		// used in v1 (profile + data)
		
		GetUserModel user = new GetUserModel();
		boolean isGetUserSuccess = true;
		
		try {
			// get user from user table
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT name, email, phone FROM users"
					+ " WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rsUser = stmt.executeQuery();
			if (rsUser.next()) {
				user.Profile.Name = rsUser.getString("name");
				user.Profile.Email = rsUser.getString("email");
				user.Profile.Phone = rsUser.getString("phone");
			} else {
				isGetUserSuccess = false;
			}
			
			if (isGetUserSuccess) {
				// get transactions of user from transactions table
				stmt = conn.prepareStatement("SELECT amount, bank_account, transaction_date, recipient_id, sender_id FROM `transactions` " 
								+ "WHERE recipient_id = ? OR sender_id = ?");
				stmt.setInt(1, id);
				stmt.setInt(2, id);
				ResultSet rsTransactions = stmt.executeQuery();
				while (rsTransactions.next()) {
					GetTransactionModel tx = new GetTransactionModel();
					tx.Amount = rsTransactions.getDouble("amount");
					tx.BankAccount = rsTransactions.getString("bank_account");
					tx.TransactionDate = rsTransactions.getDate("transaction_date");
					tx.RecipientId = rsTransactions.getInt("recipient_id");
					tx.SenderId = rsTransactions.getInt("sender_id");
					user.Transactions.add(tx);
				}
				
				stmt = conn.prepareStatement("SELECT alias, user_id, contact_id FROM `contacts`" 
						+ " WHERE user_id = ?");
				stmt.setInt(1, id);
				ResultSet rsContacts = stmt.executeQuery();
				while (rsContacts.next()) {
					GetContactModel tx = new GetContactModel();
					tx.Alias = rsContacts.getString("alias");
					tx.UserId = rsContacts.getInt("user_id");
					tx.ContactId = rsContacts.getInt("contact_id");
					user.Contacts.add(tx);
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public Boolean IsEmailUsed(CreateUserModel user) {
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
	
	public int CreateUser(CreateUserModel user) {
		
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
