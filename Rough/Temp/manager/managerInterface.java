package com.highradius.training.manager;

import java.util.Map;

public interface managerInterface {
			public Map<String,Object> dataFetch(int start,int limit);
			public void dataAdd(String title,String director,Integer releaseYear,String language,String description,String rating,String specialFeatures);
			public void dataEdit(Integer filmId,String title,String director,Integer releaseYear,String language,String description,String rating);
			public void dataDelete(Integer filmId);
}
