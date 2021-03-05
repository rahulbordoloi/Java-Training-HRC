package com.highradius.struts.action;
import com.google.gson.Gson;
import com.highradius.struts.manager.SakilaGetData;
import com.highradius.struts.model.FilmPojo;

import java.sql.*;
import java.util.*;

public class SakilaGetAction {
	
	// Request Parameters
	private Integer start, limit, nullFlag;

	// Response Parameter
	private String dataResponse;

	// JDBC Variables Information
	private Connection dbConnection = null;
	private PreparedStatement prStmt = null;
	private ResultSet rS = null;
		
	private String url = "jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull";
	private String userName = "root";
	private String passWord = "root";
	private String query = "";
	private ArrayList<FilmPojo> arr = new ArrayList<>();
	private HashMap<String, Object> responseData = new HashMap<>();
	private int numberOfRows = 0;

	/* ####################################################################################
	#                            `getData` Execute Function                               #
	#################################################################################### */
	public String getData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling GetData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Checking out for Pagination Requests
			if((this.start == null)|| (this.limit == null))
				this.nullFlag = 1;
			else
				this.nullFlag = 0;
				
			// Open a Connection
			this.dbConnection = DriverManager.getConnection(this.url, this.userName, this.passWord);
			if(this.dbConnection != null)	
				System.out.println("DB Connected!");
			
			// SQL Query String and Prepared Statement Generation
			this.query = "SELECT \r\n"
					+ "film_data.film_id,\r\n"
					+ "film_data.title,\r\n"
					+ "film_data.description,\r\n"
					+ "film_data.release_year,\r\n"
					+ "lang.name AS `language`,\r\n"
					+ "film_data.original_language_id,\r\n"
					+ "film_data.rental_duration,\r\n"
					+ "film_data.rental_rate,\r\n"
					+ "film_data.length,\r\n"
					+ "film_data.replacement_cost,\r\n"
					+ "film_data.rating,\r\n"
					+ "film_data.special_features,\r\n"
					+ "film_data.last_update,\r\n"
					+ "film_data.director\r\n"
					+ "FROM film AS film_data\r\n"
					+ "LEFT JOIN `language` AS lang ON film_data.language_id = lang.language_id;"; 
			
			// If START and LIMIT are NOT Defined.
			if(this.nullFlag == 1)
				prStmt = this.dbConnection.prepareStatement(this.query);

			// If START and LIMIT are Defined.
			else {
				this.query = this.query.replaceAll(";", "") + "\r\nLIMIT ?, ?;";
				prStmt = this.dbConnection.prepareStatement(this.query);
				prStmt.setInt(1, start);
				prStmt.setInt(2, limit);

			}
			
			// Execute SQL Query
			System.out.println("Executing Query...");
			this.rS = this.prStmt.executeQuery();
			
			// Extract Data from Result Set
			while(rS.next()) {

				FilmPojo obj = new FilmPojo();
				obj.setFilm_id(this.rS.getInt("film_id"));
				obj.setTitle(this.rS.getString("title"));
				obj.setDescription(this.rS.getString("description"));
				obj.setRelease_year(this.rS.getLong("release_year"));
				obj.setLanguage(this.rS.getString("language"));
				obj.setOriginal_language_id(this.rS.getInt("original_language_id"));
				obj.setRental_duration(this.rS.getInt("rental_duration"));
				obj.setRental_rate(this.rS.getDouble("rental_rate"));
				obj.setLength(this.rS.getLong("length"));
				obj.setReplacement_cost(this.rS.getDouble("replacement_cost"));
				obj.setRating(this.rS.getString("rating"));
				obj.setSpecial_features(this.rS.getString("special_features"));
				obj.setLast_update(this.rS.getDate("last_update"));
				obj.setDirector(this.rS.getString("director"));
				
				// Adding (Appending) Line by Line Parse of SQl Query
				this.arr.add(obj);	

			}
			rS.close();

			// Acquiring Number of Rows in DB
			this.query = "SELECT COUNT(*) as Number_Of_Rows FROM film;";
			this.prStmt = this.dbConnection.prepareStatement(this.query);
			this.rS = this.prStmt.executeQuery();
			
			// Extracting Data from Result Set
			while(this.rS.next()) {
				numberOfRows = this.rS.getInt("Number_Of_Rows");
				break;
			}

			// Converting the Same to JSON Object
			Gson gson = new Gson();
			responseData.put("success", true);
			responseData.put("totalCount", numberOfRows);
			responseData.put("filmData", this.arr);
			setDataResponse(gson.toJson(responseData));
			
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

	public String getDataResponse() {
		return dataResponse;
	}

	public void setDataResponse(String dataResponse) {
		this.dataResponse = dataResponse;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
