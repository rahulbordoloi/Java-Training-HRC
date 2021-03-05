package com.highradius.struts.controllers;
import com.highradius.struts.action.SakilaAddAction;
import com.google.gson.Gson;
import com.highradius.struts.model.FilmPojo;

public class SakilaAddData extends SakilaAddAction {

	public String addData() {
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
			this.movieReleaseYear = this.movieReleaseYear != null ? this.movieReleaseYear : 2021;
			this.movieLanguage = this.movieLanguage != null ? this.movieLanguage : "English";
			this.movieDirector = this.movieDirector != null ? this.movieDirector : "";
			this.movieRating = this.movieRating != null ? this.movieRating : "";
			this.movieSpecialFeatures = this.movieSpecialFeatures != null ? this.movieSpecialFeatures : "";
				
			// Open a Connection
			this.dbConnection = DriverManager.getConnection(this.url, this.userName, this.passWord);
			if(this.dbConnection != null)	
				System.out.println("DB Connected!");
			
			// SQL Query String and Prepared Statement Generation
			this.query = "INSERT INTO film (title, `description`, release_year, language_id, director, rating, special_features)\r\n"
			+ "VALUES (?, ?, ?, (SELECT language_id FROM `language` WHERE `name` = ?), ?, ?, ?)";
			
			this.prStmt = dbConnection.prepareStatement(this.query);
			this.prStmt.setString(1, this.movieTitle);
			this.prStmt.setString(2, this.movieDescription);
			this.prStmt.setLong(3, this.movieReleaseYear);
			this.prStmt.setString(4, this.movieLanguage);
			this.prStmt.setString(5, this.movieDirector);
			this.prStmt.setString(6, this.movieRating);
			this.prStmt.setString(7, this.movieSpecialFeatures);
			
			// Execute SQL Query
			System.out.println("Query Associated: " + this.prStmt);
			System.out.println("Executing Query...");
			this.prStmt.executeUpdate();
			System.out.println("Query Sucessful! Inserted 1 Row in DB.");

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