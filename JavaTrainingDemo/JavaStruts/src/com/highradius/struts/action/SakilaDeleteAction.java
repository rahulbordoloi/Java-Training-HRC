package com.highradius.struts.action;
import com.google.gson.Gson;
import com.highradius.struts.controllers.SakilaGetData;
import com.highradius.struts.model.FilmPojo;

import java.sql.*;
import java.util.*;

public class SakilaDeleteAction {
	
	// Request Parameters
	private String film_id;

	// JDBC Variables Information
	private Connection dbConnection = null;
	private PreparedStatement prStmt = null;
	private ResultSet rS = null;
		
	private String url = "jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull";
	private String userName = "root";
	private String passWord = "root";
	private String query = "";

	/* ####################################################################################
	#                           `deleteData` Execute Function                                #
	#################################################################################### */
	public String deleteData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling DeleteData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			// Open a Connection
			this.dbConnection = DriverManager.getConnection(this.url, this.userName, this.passWord);
			if(this.dbConnection != null)	
				System.out.println("DB Connected!");

			// Making the Request into Suitable Format
			String[] filmIdListString = this.film_id.split(",");
			ArrayList<Integer> filmIdList = new ArrayList<>();
			for(String id : filmIdListString) {
				filmIdList.add(Integer.parseInt(id));
			}
			
			// Using For Loop to Perform the Task
						
			for(int id : filmIdList) {
							
				// SQL Query String and Prepared Statement Generation
				this.query = "DELETE FROM film WHERE film_id = ?;";
				this.prStmt = this.dbConnection.prepareStatement(this.query);
				this.prStmt.setInt(1, id);
				
				// Execute SQL Query
				System.out.println("Query Associated: " + this.prStmt);
				System.out.println("Executing Query...");
				this.prStmt.executeUpdate();

			}

			System.out.println(String.format("Query Sucessful! Deleted %d Row(s) from DB.", filmIdList.size()));
							
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

	public String getFilm_id() {
		return film_id;
	}

	public void setFilm_id(String film_id) {
		this.film_id = film_id;
	}

}
