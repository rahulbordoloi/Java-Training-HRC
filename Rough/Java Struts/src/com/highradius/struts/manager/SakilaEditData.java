package com.highradius.struts.manager;

import java.sql.DriverManager;
import com.highradius.struts.action.SakilaAction;

public class SakilaEditData extends SakilaAction {

	/* ####################################################################################
	#                           `editData` Execute Function                                #
	#################################################################################### */
	public String editSakilaData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling EditData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			// Open a Connection
			super.dbConnection = DriverManager.getConnection(super.url, super.userName, super.passWord);
			if(super.dbConnection != null)	
				System.out.println("DB Connected!");
			
			// SQL Query String and Prepared Statement Generation
			super.query = "UPDATE film \r\n"
			+ "SET title = ?, \r\n"
			+ "`description` = ?,\r\n"
			+ "release_year = ?,\r\n"
			+ "language_id = (SELECT language_id FROM `language` WHERE `name` = ?),\r\n"
			+ "director = ?,\r\n"
			+ "rating = ?,\r\n"
			+ "special_features = ?\r\n"
			+ "WHERE film_id = ?;";
			
			super.prStmt = dbConnection.prepareStatement(super.query);
			super.prStmt.setString(1, super.title);
			super.prStmt.setString(2, super.description);
			super.prStmt.setLong(3, super.release_year);
			super.prStmt.setString(4, super.language);
			super.prStmt.setString(5, super.director);
			super.prStmt.setString(6, super.rating);
			super.prStmt.setString(7, super.special_features);
			super.prStmt.setInt(8, super.film_id);
			
			// Execute SQL Query
			System.out.println("Query Associated: " + super.prStmt);
			System.out.println("Executing Query...");
			super.prStmt.executeUpdate();
			System.out.println("Query Sucessful! Updated 1 Row in DB.");

			// Closing DB Connection
			super.dbConnection.close();
			super.prStmt.close();
			
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
