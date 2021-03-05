package com.highradius.struts.action;
import com.google.gson.Gson;
import com.highradius.struts.controllers.SakilaGetData;
import com.highradius.struts.model.FilmPojo;

import java.sql.*;
import java.util.*;

public class SakilaAddAction {
	
	// Request Parameters
	private String movieTitle, movieDescription, movieLanguage, movieDirector, movieRating, movieSpecialFeatures;
	private Long movieReleaseYear;

	// JDBC Variables Information
	private Connection dbConnection = null;
	private PreparedStatement prStmt = null;
	private ResultSet rS = null;
		
	private String url = "jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull";
	private String userName = "root";
	private String passWord = "root";
	private String query = "";

	/* ####################################################################################
	#                           `addData` Execute Function                                #
	#################################################################################### */
	public String addData() {
		
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

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public String getMovieSpecialFeatures() {
		return movieSpecialFeatures;
	}

	public void setMovieSpecialFeatures(String movieSpecialFeatures) {
		this.movieSpecialFeatures = movieSpecialFeatures;
	}

	public Long getMovieReleaseYear() {
		return movieReleaseYear;
	}

	public void setMovieReleaseYear(Long movieReleaseYear) {
		this.movieReleaseYear = movieReleaseYear;
	}

}
