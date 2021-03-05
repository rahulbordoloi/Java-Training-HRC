package com.highradius.struts.action;
import com.google.gson.Gson;
import com.highradius.struts.manager.SakilaGetData;
import com.highradius.struts.model.FilmPojo;

import java.sql.*;
import java.util.*;

public class SakilaEditAction {
	
	// Request Parameters
	private int film_id;
	private String title, description, language, director, rating, special_features;
	private Long release_year;

	// JDBC Variables Information
	private Connection dbConnection = null;
	private PreparedStatement prStmt = null;
	private ResultSet rS = null;
		
	private String url = "jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull";
	private String userName = "root";
	private String passWord = "root";
	private String query = "";

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

	public int getFilm_id() {
		return film_id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecial_features() {
		return special_features;
	}

	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}

	public Long getRelease_year() {
		return release_year;
	}

	public void setRelease_year(Long release_year) {
		this.release_year = release_year;
	}

}
