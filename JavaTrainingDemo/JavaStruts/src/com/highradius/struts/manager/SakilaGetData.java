package com.highradius.struts.manager;

import java.sql.DriverManager;
import java.util.HashMap;
import com.highradius.struts.action.SakilaAction;
import com.highradius.struts.model.FilmPojo;

public class SakilaGetData extends SakilaAction {

	// `getData` Execute Function
	public HashMap<String, Object> geSakilatData() {

		System.out.println("*".repeat(50));
		System.out.println("Calling GetData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Checking out for Pagination Requests
			if((super.start == null) || (super.limit == null))
				super.nullFlag = 1;
			else
				super.nullFlag = 0;
				
			// Open a Connection
			super.dbConnection = DriverManager.getConnection(super.url, super.userName, super.passWord);
			if(super.dbConnection != null)	
				System.out.println("DB Connected!");
			
			// SQL Query String and Prepared Statement Generation
			super.query = "SELECT \r\n"
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
			if(super.nullFlag == 1)
				prStmt = super.dbConnection.prepareStatement(super.query);

			// If START and LIMIT are Defined.
			else {
				super.query = super.query.replaceAll(";", "") + "\r\nLIMIT ?, ?;";
				prStmt = super.dbConnection.prepareStatement(super.query);
				prStmt.setInt(1, start);
				prStmt.setInt(2, limit);

			}
			
			// Execute SQL Query
			System.out.println("Executing Query...");
			super.rS = super.prStmt.executeQuery();
			
			// Extract Data from Result Set
			while(rS.next()) {

				FilmPojo obj = new FilmPojo();
				obj.setFilm_id(super.rS.getInt("film_id"));
				obj.setTitle(super.rS.getString("title"));
				obj.setDescription(super.rS.getString("description"));
				obj.setRelease_year(super.rS.getLong("release_year"));
				obj.setLanguage(super.rS.getString("language"));
				obj.setOriginal_language_id(super.rS.getInt("original_language_id"));
				obj.setRental_duration(super.rS.getInt("rental_duration"));
				obj.setRental_rate(super.rS.getDouble("rental_rate"));
				obj.setLength(super.rS.getLong("length"));
				obj.setReplacement_cost(super.rS.getDouble("replacement_cost"));
				obj.setRating(super.rS.getString("rating"));
				obj.setSpecial_features(super.rS.getString("special_features"));
				obj.setLast_update(super.rS.getDate("last_update"));
				obj.setDirector(super.rS.getString("director"));
				
				// Adding (Appending) Line by Line Parse of SQl Query
				super.arr.add(obj);	

			}
			rS.close();

			// Acquiring Number of Rows in DB
			super.query = "SELECT COUNT(*) as Number_Of_Rows FROM film;";
			super.prStmt = super.dbConnection.prepareStatement(super.query);
			super.rS = super.prStmt.executeQuery();
			
			// Extracting Data from Result Set
			while(super.rS.next()) {
				numberOfRows = super.rS.getInt("Number_Of_Rows");
				break;
			}

			// Converting the HashMap into Response
			responseData.put("success", true);
			responseData.put("totalCount", numberOfRows);
			responseData.put("filmData", super.arr);
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Execution Over!");
		}

		return responseData;
	}
	
}
