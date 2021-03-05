package com.highradius.struts.controllers;
import java.sql.DriverManager;

import com.highradius.struts.action.SakilaEditAction;

public class SakilaEditData extends SakilaEditAction {

	/* ####################################################################################
	#                           `editData` Execute Function                                #
	#################################################################################### */
	public String editData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling EditData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			// Open a Connection
			this.dbConnection = DriverManager.getConnection(this.url, this.userName, this.passWord);
			if(this.dbConnection != null)	
				System.out.println("DB Connected!");
			
			// SQL Query String and Prepared Statement Generation
			this.query = "UPDATE film \r\n"
			+ "SET title = ?, \r\n"
			+ "`description` = ?,\r\n"
			+ "release_year = ?,\r\n"
			+ "language_id = (SELECT language_id FROM `language` WHERE `name` = ?),\r\n"
			+ "director = ?,\r\n"
			+ "rating = ?,\r\n"
			+ "special_features = ?\r\n"
			+ "WHERE film_id = ?;";
			
			this.prStmt = dbConnection.prepareStatement(this.query);
			this.prStmt.setString(1, this.title);
			this.prStmt.setString(2, this.description);
			this.prStmt.setLong(3, this.release_year);
			this.prStmt.setString(4, this.language);
			this.prStmt.setString(5, this.director);
			this.prStmt.setString(6, this.rating);
			this.prStmt.setString(7, this.special_features);
			this.prStmt.setInt(8, this.film_id);
			
			// Execute SQL Query
			System.out.println("Query Associated: " + this.prStmt);
			System.out.println("Executing Query...");
			this.prStmt.executeUpdate();
			System.out.println("Query Sucessful! Updated 1 Row in DB.");

			// Closing DB Connection
			this.dbConnection.close();
			this.prStmt.close();
			
		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		finally {
			System.out.println("Execution Over!");
		}

		return "success";

	}

}
