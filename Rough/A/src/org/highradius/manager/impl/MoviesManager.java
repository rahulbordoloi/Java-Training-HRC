package org.highradius.manager.impl;

import java.util.*;

import org.highradius.dao.MoviesDao;
import org.highradius.manager.MoviesManagerImpl;
import org.highradius.model.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MoviesManager implements MoviesManagerImpl {
	private MoviesDao md;
	
	public MoviesDao getMd() {
		return md;
	}

	public void setMd(MoviesDao md) {
		this.md = md;
	}
	
	public Movie sanityCheck(Movie ob) {
		if(ob.getDescription() == "")
			ob.setDescription(null);
		if(ob.getRating() == "")
			ob.setRating(null);
		if(ob.getSpecial_features() == "")
			ob.setSpecial_features(null);
		else
			ob.setSpecial_features(ob.getSpecial_features().replaceAll("[,]\\s+", ","));
		if(ob.getRelease_year() == "")
			ob.setRelease_year(null);
		return ob;
	}

	public HashMap<String, Object> loadData(){
		
		return md.loadData();
	}
	
	public HashMap<String, Object> langData(){
		
		return md.langData();
	}

	public HashMap<String, Object> editData(Movie ob) {
		
		return md.editData(sanityCheck(ob));
	}

	public HashMap<String, Object> deleteData(String ids) {
		
		return md.deleteData(ids);
	}
	
	public HashMap<String, Object> addData(Movie ob) {
		
		return md.addData(sanityCheck(ob));
	}

}
