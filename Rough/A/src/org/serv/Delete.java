package org.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Delete
 */
/* @WebServlet("/DeletePath") */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
				 sb.append(line).append('\n'); 
	        }
	    } finally {
	        reader.close();
	    }
	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root" , "root");
			response.setContentType("text/html;charset=UTF-8");
	
			String sql = "UPDATE film SET isDeleted = 1 WHERE film_id IN ("+sb.substring(1, sb.length()-2)+");";
			//statement=connection.createStatement();
			PreparedStatement ps;
			//ps.setInt(1,500);
			ps = con.prepareStatement(sql);
			System.out.println("del");
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		/*
		 * for(int i = 0; i<sb.length(); i++) { System.out.println(sb.charAt(i)); }
		 */
	    
	}

}
