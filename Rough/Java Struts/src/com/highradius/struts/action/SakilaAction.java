package com.highradius.struts.action;

import com.google.gson.Gson;
import com.highradius.struts.manager.SakilaAddData;
import com.highradius.struts.manager.SakilaDeleteData;
import com.highradius.struts.manager.SakilaEditData;
import com.highradius.struts.manager.SakilaGetData;
import com.highradius.struts.model.FilmPojo;
import java.sql.*;
import java.util.*;

public class SakilaAction {
	
	// Request Parameters
	public Integer start, limit, film_id, nullFlag;
	public String title, description, language, director, rating, special_features, del_filmIds;
	public Long release_year;

	// Response Parameter
	private String dataResponse;

	// JDBC Variables Information
	public Connection dbConnection = null;
	public PreparedStatement prStmt = null;
	public ResultSet rS = null;
		
	public String url = "jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull";
	public String userName = "root";
	public String passWord = "root";
	public String query = "";
	public ArrayList<FilmPojo> arr = new ArrayList<>();
	public HashMap<String, Object> responseData = new HashMap<>();
	public int numberOfRows = 0;

	// Sakila GetData Execute Function
	public String getData() {

		try {

			SakilaGetData dataClass = new SakilaGetData();
			this.responseData = dataClass.geSakilatData();

			// Converting the Same to JSON Object
			Gson gson = new Gson();
			setDataResponse(gson.toJson(this.responseData));

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
	
	// Sakila AddData Execute Function
	public String addData() {
		
		SakilaAddData dataClass = new SakilaAddData();
		return dataClass.addSakilaData();

	}

	// Sakila EditData Execute Function
	public String editData() {

		SakilaEditData dataClass = new SakilaEditData();
		return dataClass.editSakilaData();

	}

	// Sakila DeleteData Execute Function
	public String deleteData() {

		SakilaDeleteData dataClass = new SakilaDeleteData();
		return dataClass.deleteSakilaData();
		
	}

	// Getters and Setters
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
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
	public Long getRelease_year() {
		return release_year;
	}
	public void setRelease_year(Long release_year) {
		this.release_year = release_year;
	}
	public String getDataResponse() {
		return dataResponse;
	}
	public void setDataResponse(String dataResponse) {
		this.dataResponse = dataResponse;
	}
	public String getDel_filmIds() {
		return del_filmIds;
	}
	public void setDel_filmIds(String del_filmIds) {
		this.del_filmIds = del_filmIds;
	}
}
