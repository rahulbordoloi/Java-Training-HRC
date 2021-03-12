package org.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LangServ
 */
/* @WebServlet("/LangPath") */
public class LangServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LangServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ResultSet resultset=null;
		
		List<Lang> list = new ArrayList<Lang>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
			response.setContentType("text/html;charset=UTF-8");
		
			Lang movie= null;
			
			String sql = "select * from language;";
			//statement=connection.createStatement();
			PreparedStatement ps=con.prepareStatement(sql);
			//ps.setInt(1,500);
			resultset=ps.executeQuery();
			
			while(resultset.next()) {
				movie = new Lang();
				movie.setLanguage_id(resultset.getInt(1));
				movie.setName(resultset.getString(2));
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
