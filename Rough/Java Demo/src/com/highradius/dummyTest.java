package com.highradius;

import java.sql.Array;
import java.util.ArrayList;

public class dummyTest {

	public dummyTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Inside Dummy Test Java Class");
		
		// Use Case - II
//		System.out.println("Number of Rows: " + NumberOfEntries.noOfRows());
//		String query = "SELECT \r\n"
//				+ "film_data.film_id,\r\n"
//				+ "film_data.title,\r\n"
//				+ "film_data.description,\r\n"
//				+ "film_data.release_year,\r\n"
//				+ "lang.name AS `language`,\r\n"
//				+ "film_data.original_language_id,\r\n"
//				+ "film_data.rental_duration,\r\n"
//				+ "film_data.rental_rate,\r\n"
//				+ "film_data.length,\r\n"
//				+ "film_data.replacement_cost,\r\n"
//				+ "film_data.rating,\r\n"
//				+ "film_data.special_features,\r\n"
//				+ "film_data.last_update,\r\n"
//				+ "film_data.director\r\n"
//				+ "FROM film AS film_data\r\n"
//				+ "LEFT JOIN `language` AS lang ON film_data.language_id = lang.language_id;";
//		System.out.println(query);
		
		// Use Case - I
//		String s = new String("2020-07-07");
//		Long year = Long.parseLong(s.substring(0, 4));
//		System.out.println("Year: " + year);
		
		// Use Case - III
//		String x = "Rahul"; // null;
//		String y = x != null ? x : "Hello";
//		System.out.println("Y: " + y);
//		System.out.println("2006".substring(0, 4));
		
//		// Use Case - IV
//		System.out.println("*".repeat(50));
		
//		// Use Case - V
//		Integer x = 7;
//		System.out.println(String.format("The Value is %d", x));
		
		// Use Case - VI
		String s = "101, 102";
		String[] filmIdList = s.split(", "); 
		for(int i = 0; i < filmIdList.length; i++) {
			System.out.println(filmIdList[i]);
		}
		ArrayList<Integer> x = new ArrayList<>();
		for(String i : filmIdList) {
			x.add(Integer.parseInt(i));
		}
		System.out.println(x);
		
	}

}