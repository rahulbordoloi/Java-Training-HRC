package org.highradius.action;

import java.util.HashMap;

import org.highradius.manager.impl.MoviesManager;
import org.highradius.model.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MoviesAction extends Movie {
	private HashMap<String, Object> result = new HashMap<String,Object>();
	private String selected_ids;
	
	public String getSelected_ids() {
		return selected_ids;
	}

	public void setSelected_ids(String selected_ids) {
		this.selected_ids = selected_ids;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}
	
	public String loadData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MoviesManager mm = (MoviesManager)context.getBean("moviesManager");
		result = mm.loadData();
		
		return "success";
	}
	
	public String langData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MoviesManager mm = (MoviesManager)context.getBean("moviesManager");
		result = mm.langData();
		
		return "success";
	}
	
	public String editData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Movie movie = (Movie)context.getBean("movies");
		
		//Movie movie = new Movie();
		movie.setTitle(getTitle());
		movie.setDescription(getDescription());
		movie.setDirector(getDirector());
		movie.setFilm_id(getFilm_id());
		movie.setLanguage_name(getLanguage_name());
		movie.setRating(getRating());
		movie.setRelease_year(getRelease_year());
		movie.setSpecial_features(getSpecial_features());
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MoviesManager mm = (MoviesManager)context.getBean("moviesManager");
		result = mm.editData(movie);
		
		return "success";
	}
	
	public String deleteData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MoviesManager mm = (MoviesManager)context.getBean("moviesManager");
		result = mm.deleteData(getSelected_ids());
		
		return "success";
	}
	
	public String addData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Movie movie = (Movie)context.getBean("movies");
		
		//Movie movie = new Movie();
		movie.setTitle(getTitle());
		movie.setDescription(getDescription());
		movie.setDirector(getDirector());
		movie.setLanguage_name(getLanguage_name());
		movie.setRating(getRating());
		movie.setRelease_year(getRelease_year());
		movie.setSpecial_features(getSpecial_features());
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MoviesManager mm = (MoviesManager)context.getBean("moviesManager");
		result = mm.addData(movie);
		
		return "success";
	}
}
