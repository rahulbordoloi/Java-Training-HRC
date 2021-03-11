package com.highradius.training.dao;

import java.sql.SQLException;
import java.util.Map;

public interface daoInterface {
	public Map<String,Object> dataFetch(int start,int limit);
	public void dataAdd(String title,String director,Integer releaseYear,String language,String description,String rating,String specialFeatures);
	public void dataEdit(Integer filmId,String title,String director,Integer releaseYear,String language,String description,String rating);
	public void dataDelete(Integer filmId);
}
