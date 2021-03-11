package com.highradius.training.struts.action;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.training.managerImpl.managerImplementation;

import project.Sakila_pojo;
public class Action {
	
		private int start;
		private int limit;
		private Integer filmId;
		private String title;
		private Integer realeaseYear;
		private String specialFeatures;
		private String rating;
		private String director;
		private String description;
		private String languageName;
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		
		public int getFilmId() {
			return filmId;
		}
		public void setFilmId(int filmId) {
			this.filmId = filmId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getReleaseYear() {
			return realeaseYear;
		}
		public void setReleaseYear(int releaseYear) {
			this.realeaseYear = releaseYear;
		}
		public String getSpecialFeatures() {
			return specialFeatures;
		}
		public void setSpecialFeatures(String specialFeatures) {
			this.specialFeatures = specialFeatures;
		}
		public String getRating() {
			return rating;
		}
		public void setRating(String rating) {
			this.rating = rating;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getLanguage() {
			return languageName;
		}
		public void setLanguage(String languageName) {
			System.out.println("Language"+languageName);
			this.languageName = languageName;
		}
		public Map<String, Object> getMap() {
			return map;
		}
		public void setMap(Map<String, Object> map) {
			this.map = map;
		}
		
		//Fetching data
		public String dataFetch() {
			String temp="Failure";
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				managerImplementation ob=(managerImplementation) context.getBean("manager");
				setMap(ob.dataFetch(getStart(), getLimit()));
				temp="success";
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return temp;
			
		}
		
		
		//Adding record
		
		public String dataAdd() {
			System.out.println(getTitle()+"title ");
			System.out.println(getDirector()+"director ");
			System.out.println(getReleaseYear()+"getReleaseYear ");
			System.out.println(getLanguage()+"getLanguage ");
			System.out.println(getDescription()+"getDescription ");
			System.out.println(getRating()+"getRating ");
			String temp="Failure";
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				managerImplementation ob=(managerImplementation) context.getBean("manager");
				System.out.println("hERE");
				ob.dataAdd(getTitle(),getDirector(),getReleaseYear(),getLanguage(),
					getDescription(),getRating(),getSpecialFeatures());
				
				temp="success";
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return temp;
		}
		
		//edit record
		public String dataEdit() {
			String temp="Failure";
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				managerImplementation ob=(managerImplementation) context.getBean("manager");
				
				ob.dataEdit(getFilmId(),getTitle(),getDirector(),getReleaseYear(),getLanguage(),
					getDescription(),getRating());
				temp="success";
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return temp;
			
		}
//deleting record
		public String dataDelete() {
			String temp="Failure";
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				managerImplementation ob=(managerImplementation) context.getBean("manager");
				ob.dataDelete(getFilmId());
				temp="success";
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return temp;
		}


		
}
