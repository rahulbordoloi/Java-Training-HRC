package org.struts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*
;
public class LoadData {
	/*
	 * private List<Movie> movie_list = new ArrayList<Movie>(); private Integer
	 * count;
	 */
	private HashMap<String, Object> result = new HashMap<String,Object>(); 

	/*
	 * public List<Movie> getMovie_list() { return movie_list; }
	 * 
	 * public void setMovie_list(List<Movie> movie_list) { this.movie_list =
	 * movie_list; }
	 * 
	 * public Integer getCount() { return count; }
	 * 
	 * public void setCount(Integer count) { this.count = count; }
	 */
	
	
	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String execute () {
		ResultSet resultset=null;
		
		List<Movie> list = new ArrayList<Movie>();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
			/* response.setContentType("text/html;charset=UTF-8"); */
		
			Movie movie= null;
			
			String sql = "SELECT \r\n" + 
					"f.film_id,\r\n" + 
					"f.title, \r\n" + 
					"f.description, \r\n" + 
					"f.release_year, \r\n" + 
					"l.name AS LANGUAGE, \r\n" + 
					"f.rating, \r\n" + 
					"f.special_features,   \r\n" +
					"f.director   \r\n" +
					"FROM film f JOIN LANGUAGE l ON f.language_id = l.language_id where isDeleted = 0;";
			
			PreparedStatement ps=con.prepareStatement(sql);
			resultset=ps.executeQuery();
			
			while(resultset.next()) {
				movie = new Movie();
				movie.setFilm_id(resultset.getInt(1));
				movie.setTitle(resultset.getString(2));
				movie.setDescription(resultset.getString(3));
				movie.setRelease_year(resultset.getString(4));
				movie.setLanguage(resultset.getString(5));
				movie.setRating(resultset.getString(6));
				movie.setSpecial_features(resultset.getString(7));
				movie.setDirector(resultset.getString(8));
				list.add(movie);
			}
			
			result.put("movie_list",list);
			result.put("count",list.size());
			
			/*
			 * movie_list = list; count = list.size();
			 */
			
			System.out.println("Load Data Success");
			return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		
	}

}
