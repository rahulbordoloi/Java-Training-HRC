package com.highradius.training.managerImpl;

import java.util.Map;

import com.highradius.training.dao.daoInterface;
import com.highradius.training.manager.managerInterface;

public class managerImplementation implements managerInterface {
	
	
	
	public daoInterface getDao() {
		return dao;
	}

	public void setDao(daoInterface dao) {
		this.dao = dao;
	}

	public daoInterface dao;
	@Override
	public Map<String, Object> dataFetch(int start,int limit) {
		// TODO Auto-generated method stub
		return dao.dataFetch(start,limit);
	}

	@Override
	public void dataAdd(String title, String director, Integer releaseYear, String language, String description,
			String rating, String specialFeatures) {
		// TODO Auto-generated method stub
		dao.dataAdd(title, director, releaseYear, language, description, rating, specialFeatures);
		
	}

	@Override
	public void dataEdit(Integer filmId, String title, String director, Integer releaseYear, String language,
			String description, String rating) {
		// TODO Auto-generated method stub
		dao.dataEdit(filmId, title, director, releaseYear, language, description, rating);
	}

	@Override
	public void dataDelete(Integer filmId) {
		// TODO Auto-generated method stub
		dao.dataDelete(filmId);
	}

}
