package com.highradius;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetFormData")
public class GetFormData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetFormData() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Calling GetFormData Servlet...");
		int x = Integer.parseInt(request.getParameter("data"));
		System.out.println("Data: " + x);
		PrintWriter documentOut = response.getWriter();
		documentOut.print("Hello World");
		documentOut.flush();
		// int x = Integer.parseInt(request.getParameter("num1"));
		// int y = Integer.parseInt(request.getParameter("num2"));
		// int answer = x + y;
		
		// System.out.println("Numbers Added!");
		// System.out.println("The Result is: " + answer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}