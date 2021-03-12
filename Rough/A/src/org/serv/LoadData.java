package org.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoadData
 */
/* @WebServlet("/LoadDataPath") */
public class LoadData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//connection
				
		ResultSet resultset=null;
		
		List<Movie> list = new ArrayList<Movie>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
			response.setContentType("text/html;charset=UTF-8");
		
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
			//statement=connection.createStatement();
			PreparedStatement ps=con.prepareStatement(sql);
			//ps.setInt(1,500);
			resultset=ps.executeQuery();
			
			while(resultset.next()) {
				movie = new Movie();
				movie.setFilm_id(resultset.getInt(1));
				movie.setTitle(resultset.getString(2));
				movie.setDescription(resultset.getString(3));
				movie.setRelease_year(resultset.getInt(4));
				movie.setLanguage(resultset.getString(5));
				movie.setRating(resultset.getString(6));
				movie.setSpecial_features(resultset.getString(7));
				movie.setDirector(resultset.getString(8));
				list.add(movie);
			}
			
			PrintWriter out = response.getWriter();
			System.out.println("list sent");
			String jsonobj = new Gson().toJson(list);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//out.print("json completed");
			out.print(jsonobj);
			out.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);	
		
	}

}
