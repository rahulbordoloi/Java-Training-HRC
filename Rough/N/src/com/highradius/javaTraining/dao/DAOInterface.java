package com.highradius.javaTraining.dao;

import java.util.HashMap;

import com.highradius.javaTraining.model.FilmPojo;

public interface DAOInterface {
	
	public HashMap<String, Object> geSakilatData(Integer start, Integer limit);
	public String addSakilaData(FilmPojo obj);
	public String editSakilaData(FilmPojo obj);
	public String deleteSakilaData(String del_filmIds);

}
