package com.highradius.javaTraining.dao;

import java.util.HashMap;

import org.hibernate.SessionFactory;

import com.highradius.javaTraining.model.FilmPojo;

public interface DAOInterface {
	
	// Helper Methods
	SessionFactory getSession();
	Integer getLanguageId(String lang_name);
	
	// Execute Methods
	public HashMap<String, Object> getSakilaData();
	public HashMap<String, Object> getSakilaLangData();
	public HashMap<String, Object> addSakilaData(FilmPojo obj);
	public HashMap<String, Object> editSakilaData(FilmPojo obj);
	public HashMap<String, Object> deleteSakilaData(String del_filmIds);

}
