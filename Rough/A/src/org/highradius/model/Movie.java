package org.highradius.model;
import javax.persistence.Table;

@Table(name="film")
public class Movie {
	
	public Movie(Integer film_id, String title, String description, String release_year, String rating,
			String special_features, String director, boolean isDeleted, Lang language) {
		super();
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language = language;
		this.rating = rating;
		this.special_features = special_features;
		this.director = director;
		this.isDeleted = isDeleted;
	}
	
	public Movie() {
		super();
	}
	
	private Integer film_id;
	private String title;
	private String description;
	private String release_year;
	private Lang language;
	private String language_name;
	private String rating;
	private String special_features;
	private String director;
	private boolean isDeleted;
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
	public Lang getLanguage() {
		return language;
	}
	public void setLanguage(Lang language) {
		this.language = language;
		setLanguage_name(language.getName());
	}
	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	
	
}
