package org.highradius.manager;

import java.util.HashMap;

import org.highradius.model.Movie;

public interface MoviesManagerImpl {
	public HashMap<String, Object> loadData();
	public HashMap<String, Object> langData();
	public HashMap<String, Object> editData(Movie ob);
	public HashMap<String, Object> addData(Movie ob);
	public HashMap<String, Object> deleteData(String ids);
}
