package com.highradius.javaTraining.model;

public class SakilaPojo {
	
	// Setting in the DataTypes of the Columns
	private Integer film_id;
	private String title;
	private String description;
	private Integer realeaseYear;
	private String LanguageName;
	private String director;
	private String rating;
	private String specialFeatures;
	
	// Getters and Setters
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
	public Integer getRealeaseYear() {
		return realeaseYear;
	}
	public void setRealeaseYear(Integer realeaseYear) {
		this.realeaseYear = realeaseYear;
	}
	public String getLanguageName() {
		return LanguageName;
	}
	public void setLanguageName(String languageName) {
		LanguageName = languageName;
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
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	
}
