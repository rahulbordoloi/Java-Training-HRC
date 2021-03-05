package com.highradius.struts.manager;

import java.sql.DriverManager;
import java.util.ArrayList;
import com.highradius.struts.action.SakilaAction;

public class SakilaDeleteData extends SakilaAction {

	/* ####################################################################################
	#                           `deleteData` Execute Function                             #
	#################################################################################### */
	public String deleteSakilaData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling DeleteData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Registering JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			// Open a Connection
			super.dbConnection = DriverManager.getConnection(super.url, super.userName, super.passWord);
			if(super.dbConnection != null)	
				System.out.println("DB Connected!");

			// Making the Request into Suitable Format
			String[] filmIdListString = super.del_filmIds.split(",");
			ArrayList<Integer> filmIdList = new ArrayList<>();
			for(String id : filmIdListString) {
				filmIdList.add(Integer.parseInt(id));
			}
			
			// Using For Loop to Perform the Task
						
			for(int id : filmIdList) {
							
				// SQL Query String and Prepared Statement Generation
				super.query = "DELETE FROM film WHERE film_id = ?;";
				super.prStmt = super.dbConnection.prepareStatement(super.query);
				super.prStmt.setInt(1, id);
				
				// Execute SQL Query
				System.out.println("Query Associated: " + super.prStmt);
				System.out.println("Executing Query...");
				super.prStmt.executeUpdate();

			}

			System.out.println(String.format("Query Sucessful! Deleted %d Row(s) from DB.", filmIdList.size()));
							
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
