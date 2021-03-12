package org.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LangData {
	private List<Lang> lang_list = new ArrayList<Lang>();
	private Integer count;
	
	public List<Lang> getLang_list() {
		return lang_list;
	}

	public void setLang_list(List<Lang> lang_list) {
		this.lang_list = lang_list;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public String execute() {
		ResultSet resultset=null;
		
		List<Lang> list = new ArrayList<Lang>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
		
			Lang movie= null;
			
			String sql = "select * from language;";
			//statement=connection.createStatement();
			PreparedStatement ps=con.prepareStatement(sql);
			//ps.setInt(1,500);
			resultset=ps.executeQuery();
			
			while(resultset.next()) {
				movie = new Lang();
				movie.setLanguage_id(resultset.getInt(1));
				movie.setName(resultset.getString(2));
				list.add(movie);
			}
			
		    lang_list = list;
			count = list.size();
			
			System.out.println("Lang Data Success");
			return "success";
		
		}
		catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
}
