package com.moneytransfer.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;


public class ConnectionManager {
	
	public static Connection Get() {
		Connection conn = null;
		String dbHostname = "localhost";
		String dbSchema = "moneyxfer";
		String dbUser = "root";
		String dbPassword = "test123";

		// jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		String connectionString = "mysql://" + dbHostname + "/" + dbSchema + "?" +
                "user=" + dbUser + "&password=" + dbPassword + 
                "&useSSL=false&serverTimezone=UTC" +
				   "&allowPublicKeyRetrieval=true";
		
	    try {
	    	String cleardbUrl = System.getenv().get("CLEARDB_DATABASE_URL");
	    	if (cleardbUrl != null) {
	    		connectionString = cleardbUrl;
	    	}		    	
	    } catch (Exception e) {
	    	
	    }
		
		// connect to database
		try {
		    conn = DriverManager.getConnection("jdbc:" + connectionString);	    
		    
		} catch (SQLException e) {
		    // handle any errors
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		}
		
		return conn;

	}
}

