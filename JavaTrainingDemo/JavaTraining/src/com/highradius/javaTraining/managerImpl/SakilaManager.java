package com.highradius.javaTraining.managerImpl;

import java.util.HashMap;

import com.highradius.javaTraining.daoImpl.SakilaDAO;
import com.highradius.javaTraining.manager.SakilaManagerInterface;
import com.highradius.javaTraining.model.FilmPojo;

public class SakilaManager implements SakilaManagerInterface {
	
	private SakilaDAO sakilaDao;
	
	public SakilaDAO getManagerDao() {
		return sakilaDao;
	}

	public void setManagerDao(SakilaDAO sakilaDao) {
		this.sakilaDao = sakilaDao;
	}
	
	public FilmPojo dataSanityCheck(FilmPojo obj) {
		
		if(obj.getDescription() == "")
			obj.setDescription(null);
		if(obj.getRating() == "")
			obj.setRating(null);
		if(obj.getSpecial_features() == "")
			obj.setSpecial_features(null);
		else
			obj.setSpecial_features(obj.getSpecial_features().replaceAll("[,]\\s+", ","));
		if(obj.getRelease_year() == "")
			obj.setRelease_year(null);
		return obj;
		
	}

	public HashMap<String, Object> getSakilaData() {
		
		return sakilaDao.getSakilaData();
		
	}
	
	public HashMap<String, Object> getSakilaLangData() {
		
		return sakilaDao.getSakilaLangData();
		
	}
	
	
	public HashMap<String, Object> addSakilaData(FilmPojo obj) {
		
		return sakilaDao.addSakilaData(dataSanityCheck(obj));
		
	}
	
	public HashMap<String, Object> editSakilaData(FilmPojo obj) {
		
		return sakilaDao.editSakilaData(dataSanityCheck(obj));
		
	}
	
	public HashMap<String, Object> deleteSakilaData(String del_filmIds) {
		
		return sakilaDao.deleteSakilaData(del_filmIds);
		
	}

}
