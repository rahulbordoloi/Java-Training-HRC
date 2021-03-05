package com.highradius.struts.manager;

import java.sql.DriverManager;
import com.highradius.struts.action.SakilaAction;

public class SakilaAddData extends SakilaAction {

	public String addSakilaData() {
		
		/* ####################################################################################
		#                           `addData` Execute Function                                #
		#################################################################################### */
		
		System.out.println("*".repeat(50));
		System.out.println("Calling AddData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Checking out for Population of Request Variables
			super.release_year = super.release_year != null ? super.release_year : 2021;
			super.language = super.language != null ? super.language : "English";
			super.director = super.director != null ? super.director : "";
			super.rating = super.rating != null ? super.rating : "";
			super.special_features = super.special_features != null ? super.special_features : "";
				
			// Open a Connection
			super.dbConnection = DriverManager.getConnection(super.url, super.userName, super.passWord);
			if(super.dbConnection != null)	
				System.out.println("DB Connected!");
			
			// SQL Query String and Prepared Statement Generation
			super.query = "INSERT INTO film (title, `description`, release_year, language_id, director, rating, special_features)\r\n"
			+ "VALUES (?, ?, ?, (SELECT language_id FROM `language` WHERE `name` = ?), ?, ?, ?)";
			
			super.prStmt = dbConnection.prepareStatement(super.query);
			super.prStmt.setString(1, super.title);
			super.prStmt.setString(2, super.description);
			super.prStmt.setLong(3, super.release_year);
			super.prStmt.setString(4, super.language);
			super.prStmt.setString(5, super.director);
			super.prStmt.setString(6, super.rating);
			super.prStmt.setString(7, super.special_features);
			
			// Execute SQL Query
			System.out.println("Query Associated: " + super.prStmt);
			System.out.println("Executing Query...");
			super.prStmt.executeUpdate();
			System.out.println("Query Sucessful! Inserted 1 Row in DB.");

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