package com.highradius;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

// @WebServlet("/add")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int x = Integer.parseInt(request.getParameter("num1"));
		int y = Integer.parseInt(request.getParameter("num2"));
		int answer = x + y;
		
		System.out.println("Numbers Added!");
		System.out.println("The Result is: " + answer);

		PrintWriter documentOut = response.getWriter();
		documentOut.print("The Sum is: " + answer);
		documentOut.flush();
	}

}