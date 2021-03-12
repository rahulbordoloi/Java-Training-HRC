package org.struts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Add {
	private String title;
	private String description;
	private String release_year;
	private String language;
	private String rating;
	private String special_features;
	private String director;
	private boolean success;
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
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
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String execute() {
		if(description == "")
			setDescription(null);
		if(rating == "")
			setRating(null);
		if(special_features == "")
			setSpecial_features(null);
		else
			special_features = special_features.replaceAll("[,]\\s+", ",");
		
		
		System.out.println(special_features);
		
		
		/*
		 * System.out.println(title); System.out.println(description);
		 * System.out.println(release_year); System.out.println(language);
		 * System.out.println(rating); System.out.println(special_features);
		 * System.out.println(director);
		 */
		PreparedStatement ps;

		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
				//statement=connection.createStatement();
				//ps.setInt(1,500);
				String sql = null;
				/*
				 * if(release_year == null) { sql =
				 * "INSERT INTO film (title, description, language_id, rating, special_features, director, isDeleted) VALUES (?,?, (SELECT language_id FROM LANGUAGE WHERE NAME = ?), ?, ?,?, 0);"
				 * ; ps = con.prepareStatement(sql); } else { sql =
				 * "INSERT INTO film (title, description, language_id, rating, special_features, director, isDeleted, release_year) VALUES (?,?,(SELECT language_id FROM LANGUAGE WHERE NAME = ?), ?, ?,?, 0, ?);"
				 * ; ps = con.prepareStatement(sql); ps.setInt(7, release_year); }
				 */
				
				sql = "INSERT INTO film (title, description, language_id, rating, special_features, director, isDeleted, release_year) VALUES (?,?,(SELECT language_id FROM LANGUAGE WHERE NAME = ?), ?, ?,?, 0, ?);";
				ps = con.prepareStatement(sql);
				System.out.println("r"+release_year+"r");
				if(release_year == "")
					release_year = null;
				ps.setString(7, release_year);
				ps.setString(1, title);
				ps.setString(2, description);
				ps.setString(3, language);
				ps.setString(4, rating);
				ps.setString(5, special_features);
				ps.setString(6, director);
				
				System.out.println(ps);
				
				System.out.println("add");
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
