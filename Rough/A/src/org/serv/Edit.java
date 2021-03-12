package org.serv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Edit
 */
/* @WebServlet("/EditPath") */
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer film_id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		Integer release_year = ((request.getParameter("release_year").equals("")) ? null  : Integer.parseInt(request.getParameter("release_year")));
		String language = request.getParameter("language");
		String rating = ((request.getParameter("rating").equals("")) ? null : request.getParameter("rating"));
		String special_features_list[] = request.getParameterValues("special_features");
		String description = ((request.getParameter("description").equals("")) ? null : request.getParameter("description"));
		
		String special_features = String.join(",", special_features_list);
		special_features = ((special_features.equals("")) ? null : special_features);
		PreparedStatement ps;

		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
				response.setContentType("text/html;charset=UTF-8");
				//statement=connection.createStatement();
				//ps.setInt(1,500);
				
				String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = (SELECT language_id FROM LANGUAGE WHERE NAME = ?), rating = ?, special_features = ?, director = ? WHERE film_id = ?;";
				ps = con.prepareStatement(sql);
				ps.setString(1, title);
				ps.setString(2, description);
				System.out.println(release_year);
				ps.setInt(3, release_year);
				ps.setString(4, language);
				ps.setString(5, rating);
				ps.setString(6, special_features);
				ps.setString(7, director);
				ps.setInt(8, film_id);
				
				/*queryStatement.setNull(1, java.sql.Types.Date);*/
				
				
				
				System.out.println("edited");
				ps.execute();
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
