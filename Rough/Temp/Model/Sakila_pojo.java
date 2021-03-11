package com.highradius.training.Model;

public class Sakila_pojo {
	Integer filmId;
	   String title;
	   String description;
	   Integer realeaseYear;
	   LanguagePojo LanguageName;
	   String director;
	   String rating;
	   LanguagePojo origiLanguageName;
	   Integer rentalDuration;
	   Float rentalRate;
	   Integer length;
	   Float replacementCost;
	   String specialFeatures;
	   public Integer getFilmId() {
		return filmId;
	}
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
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
	public LanguagePojo getLanguageName() {
		return LanguageName;
	}
	public void setLanguageName(LanguagePojo languageName) {
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
	public LanguagePojo getOrigiLanguageName() {
		return origiLanguageName;
	}
	public void setOrigiLanguageName(LanguagePojo origiLanguageName) {
		this.origiLanguageName = origiLanguageName;
	}
	public Integer getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public Float getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(Float rentalRate) {
		this.rentalRate = rentalRate;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Float getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(Float replacementCost) {
		this.replacementCost = replacementCost;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	String lastUpdate;
	  
	
	
}