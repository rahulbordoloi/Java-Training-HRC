package com.highradius;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class DBConnector extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public DBConnector() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		System.out.println("DB Connector Servlet");
		Connection dbConnection = null;
		PreparedStatement prStmt = null;
		ResultSet rS = null;
		
		String url = "jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull";
		String userName = "root";
		String passWord = "root";
		String query = "SELECT * FROM film;";
		
		try {
			
			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Open a Connection
			dbConnection = DriverManager.getConnection(url, userName, passWord);
			if(dbConnection != null)	
				System.out.println("DB Connected!");
			
			// Execute SQL Query
			prStmt = dbConnection.prepareStatement(query);
			System.out.println("Executing Query...");
			rS = prStmt.executeQuery();
			ArrayList<FilmPojo> arr = new ArrayList<>();
			
			// Extract Data from Result Set
			while(rS.next()) {
				
				FilmPojo obj = new FilmPojo();
				obj.setFilm_id(rS.getLong("film_id"));
				obj.setTitle(rS.getString("title"));
				obj.setDescription(rS.getString("description"));
				obj.setRelease_year(rS.getLong("release_year"));
				obj.setLanguage_id(rS.getInt("language_id"));
				obj.setOriginal_language_id(rS.getInt("original_language_id"));
				obj.setRental_duration(rS.getInt("rental_duration"));
				obj.setRental_rate(rS.getDouble("rental_rate"));
				obj.setLength(rS.getLong("length"));
				obj.setReplacement_cost(rS.getDouble("replacement_cost"));
				obj.setRating(rS.getString("rating"));
				obj.setSpecial_features(rS.getString("special_features"));
				obj.setLast_update(rS.getDate("last_update"));
				
				// Adding (Appending) Line by Line Parse of SQl Query
				arr.add(obj);	
			}
			
			// Converting the Same to JSON Object
			Gson gson = new Gson();
			String jsonString = gson.toJson(arr);
			PrintWriter documentOut = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			documentOut.write(jsonString);
			documentOut.flush();
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			// rS.close();
			// dbConnection.close();
			// prStmt.close();
			System.out.println("DB Connection Closed!");
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}
}