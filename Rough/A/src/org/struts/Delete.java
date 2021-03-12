package org.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {
	private String selected_ids;
	private boolean success;
	
	

	public String getSelected_ids() {
		return selected_ids;
	}

	public void setSelected_ids(String selected_ids) {
		this.selected_ids = selected_ids;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String execute() {
		System.out.println("1");
		System.out.println(selected_ids);
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
		
				String sql = "UPDATE film SET isDeleted = 1 WHERE film_id IN ("+getSelected_ids()+");";
				//statement=connection.createStatement();
				PreparedStatement ps;
				//ps.setInt(1,500);
				ps = con.prepareStatement(sql);
				System.out.println("del");
				ps.execute();
				success = true;
				return "success";
			}
			catch(Exception e) {
				e.printStackTrace();
				success = false;
				return "failure";
			}
	}

}
