package org.highradius.model;

import java.util.HashSet;
import java.util.Set;

public class Lang {
	private Integer language_id;
	private String name;
	private Set<Movie> movie = new HashSet<Movie>(); 
	
	public Set<Movie> getMovie() {
		return movie;
	}
	public void setMovie(Set<Movie> movie) {
		this.movie = movie;
	}
	public Integer getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
