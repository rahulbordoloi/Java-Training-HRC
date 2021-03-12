package org.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Edit {
	private Integer film_id;
	private String title;
	private String description;
	private String release_year;
	private String language;
	private String rating;
	private String special_features;
	private String director;
	private boolean success;
	
	public Integer getFilm_id() {
		return film_id;
	}
	public void setFilm_id(Integer film_id) {
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
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String execute() {
		  System.out.println(title); System.out.println(description);
		  System.out.println(release_year); System.out.println(language);
		  System.out.println(rating); System.out.println(special_features);
		  System.out.println(director);System.out.println(film_id);
		
		if(description == "")
			setDescription(null);
		if(rating == "")
			setRating(null);
		if(special_features != null)
			special_features = special_features.replaceAll("[,]\\s+", ",");
			
		
		PreparedStatement ps;
		 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
			
			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = (SELECT language_id FROM LANGUAGE WHERE NAME = ?), rating = ?, special_features = ?, director = ? WHERE film_id = ?;";
			ps = con.prepareStatement(sql);
			if(release_year == "")
				release_year = null;
			
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setString(3, release_year);
			ps.setString(4, language);
			ps.setString(5, rating);
			ps.setString(6, special_features);
			ps.setString(7, director);
			ps.setInt(8, film_id);
			
			/*queryStatement.setNull(1, java.sql.Types.Date);*/
			
			
			
			System.out.println("edited");
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
