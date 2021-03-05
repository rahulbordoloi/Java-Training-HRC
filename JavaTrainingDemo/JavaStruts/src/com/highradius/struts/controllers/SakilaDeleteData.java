package com.highradius.struts.controllers;
import java.sql.DriverManager;
import java.util.ArrayList;

import com.highradius.struts.action.SakilaDeleteAction;

public class SakilaDeleteData extends SakilaDeleteAction{

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

}
