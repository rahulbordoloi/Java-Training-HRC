package com.highradius.javaTraining.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import com.highradius.javaTraining.dao.DAOInterface;
import com.highradius.javaTraining.model.FilmPojo;
import com.highradius.javaTraining.model.LanguagePojo;

public class SakilaDAO implements DAOInterface {
	
	// Hibernate Session Variables Information
	private SessionFactory factory = null;
	private Session session = null;
	private Transaction transaction = null;
	private String hql = "";
	private Query query = null;
	private Criteria critera = null;
	
	// Response Variables
	private ArrayList<FilmPojo> arr = new ArrayList<>();
	private HashMap<String, Object> responseData = new HashMap<>();
	private int numberOfRows = 0;
	private List result;

	// Helper Method to Get Language Name
	public Integer getLanguageId(String languageName) {

		// Opening a Session using Hibernate
		this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		this.session = this.factory.openSession();

		// Running HQL Query
		this.query = this.session.createQuery("SELECT language_id FROM Lang WHERE name = :language");
		this.query.setString("start", languageName);
		return (Integer) this.query.uniqueResult();

	}
		
    /* ####################################################################################
	#                           `getData` Execute Function                                #
	#################################################################################### */
	@SuppressWarnings("deprecation")
	public HashMap<String, Object> geSakilatData(Integer start, Integer limit) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling GetData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {
			
			// Opening a Session using Hibernate
	       	this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			this.session = this.factory.openSession();
			
			// Opening up Transaction Session and Criteria
			this.transaction = this.session.beginTransaction();
//			this.critera = this.session.createCriteria(FilmPojo.class);
//			this.critera.addOrder(Order.asc("film_id"));
//			this.critera.setFirstResult(start);
//			this.critera.setMaxResults(limit);
			
			// Checking out for Pagination Requests
			Integer nullFlag;
			if((start == null) || (limit == null))
				nullFlag = 1;
			else
				nullFlag = 0;
					
			// HQL Query String Generation
			this.hql = "SELECT \r\n"
					+ "film_data.film_id,\r\n"
					+ "film_data.title,\r\n"
					+ "film_data.description,\r\n"
					+ "film_data.release_year,\r\n"
					+ "lang.name AS `language`,\r\n"
					+ "film_data.original_language_id,\r\n"
					+ "film_data.rental_duration,\r\n"
					+ "film_data.rental_rate,\r\n"
					+ "film_data.length,\r\n"
					+ "film_data.replacement_cost,\r\n"
					+ "film_data.rating,\r\n"
					+ "film_data.special_features,\r\n"
					+ "film_data.last_update,\r\n"
					+ "film_data.director\r\n"
					+ "FROM film AS film_data\r\n"
					+ "LEFT JOIN `language` AS lang ON film_data.language_id = lang.language_id;"; 
					
			// If START and LIMIT are NOT Defined.
			if(nullFlag == 1)
				this.query = this.session.createQuery(this.hql);

			// If START and LIMIT are Defined.
			else {
			
				this.hql = this.hql.replaceAll(";", "") + "\r\nLIMIT :start, :limit;";
				this.query = this.session.createQuery(this.hql);
				this.query.setInteger("start", start);
				this.query.setInteger("limit", limit);

			}
					
			// Execute SQL Query
			System.out.println("Executing Query...");
			// Iterator result = this.query.list().iterator();
			result = this.query.list();
			
			// Extract Data from Result [Iterator]
			// for(Iterator iterator = result.iterator(); iterator.hasNext();) {
			for(int i = 0; i < result.size(); i++) {
				
				// FilmPojo objIterator = (FilmPojo) iterator.next();
				FilmPojo obj = new FilmPojo();
				
				Object[] object = (Object[]) result.get(i);
				
				obj.setFilm_id((int) object[0]);
				obj.setTitle((String) object[1]);
				obj.setDescription((String) object[2]);
				obj.setRelease_year((long) object[3]);
				obj.setLanguage((String) object[4]);
				obj.setOriginal_language_id((int) object[5]);
				obj.setRental_rate((double) object[6]);
				obj.setLength((long) object[7]);
				obj.setReplacement_cost((double) object[8]);
				obj.setRating((String) object[9]);
				obj.setLast_update((java.sql.Date) object[10]);
				obj.setDirector((String) object[11]);
				
//				obj.setFilm_id(objIterator.getFilm_id());
//				obj.setTitle(objIterator.getTitle());
//				obj.setDescription(objIterator.getDescription());
//				obj.setRelease_year(objIterator.getRelease_year());
//				obj.setLanguage(objIterator.getLanguage());
//				obj.setOriginal_language_id(objIterator.getOriginal_language_id());
//				obj.setRental_rate(objIterator.getRental_rate());
//				obj.setLength(objIterator.getLength());
//				obj.setReplacement_cost(objIterator.getReplacement_cost());
//				obj.setRating(objIterator.getRating());
//				obj.setLast_update(objIterator.getLast_update());
//				obj.setDirector(objIterator.getDirector());
				
//				obj.setFilm_id(objIterator.getInteger("film_id"));
//				obj.setTitle(rS.getString("title"));
//				obj.setDescription(rS.getString("description"));
//				obj.setRelease_year(rS.getLong("release_year"));
//				obj.setLanguage(rS.getString("language"));
//				obj.setOriginal_language_id(rS.getInt("original_language_id"));
//				obj.setRental_duration(rS.getInt("rental_duration"));
//				obj.setRental_rate(rS.getDouble("rental_rate"));
//				obj.setLength(rS.getLong("length"));
//				obj.setReplacement_cost(rS.getDouble("replacement_cost"));
//				obj.setRating(rS.getString("rating"));
//				obj.setSpecial_features(rS.getString("special_features"));
//				obj.setLast_update(rS.getDate("last_update"));
//				obj.setDirector(rS.getString("director"));
						
				// Adding (Appending) Line by Line Parse of SQl Query
				this.arr.add(obj);	

			}
			this.session.close();

			// Acquiring Number of Rows in DB
			String sql = "SELECT COUNT(*) as Number_Of_Rows FROM film;";
			SQLQuery querySQL = session.createSQLQuery(sql);
			List<Integer> resultSQL = querySQL.list();
					
			// Extracting Data from Result Set
			this.numberOfRows = resultSQL.get(0);

			// Converting the HashMap into Response
			this.responseData.put("success", true);
			this.responseData.put("totalCount", this.numberOfRows);
			this.responseData.put("filmData", this.arr);
					
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Execution Over!");
		}
		return responseData;
	}

	/* ####################################################################################
	#                           `addData` Execute Function                                #
	#################################################################################### */
	@SuppressWarnings("deprecation")
	public String addSakilaData(FilmPojo obj) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling AddData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {
			
			LanguagePojo langobj = (LanguagePojo) this.session.load(LanguagePojo.class, getLanguageId(obj.getLanguage()));

			// Checking out for Population of Request Variables
			String title = obj.getTitle() != null ? obj.getTitle() : "Untitled Film";
			String description = obj.getDescription() != null ? obj.getDescription() : "";
			Long release_year = obj.getRelease_year() != 0 ? obj.getRelease_year() : 2021;
			String language = obj.getLanguage() != null ? obj.getLanguage() : "English";
			String  director = obj.getDirector() != null ? obj.getDirector() : "";
			String rating = obj.getRating() != null ? obj.getRating() : "";
			String special_features = obj.getSpecial_features() != null ? obj.getSpecial_features() : "";
			
//			// Add Data POJO Object
//			FilmPojo addObject =  new FilmPojo();
//			addObject.setTitle(title);
//			addObject.setDescription(description);
//			addObject.setRelease_year(release_year);
//			addObject.setLanguage(language);
//			addObject.setOriginal_language_id(1);
//			addObject.setRating(rating);
//			addObject.setSpecial_features(special_features);
//			addObject.setDirector(director);
			
			// HQL Query String Generation
			this.hql = "INSERT INTO film (title, `description`, release_year, language_id, director, rating, special_features)\r\n"
						+ "VALUES (:title, :description, :release_year, (SELECT language_id FROM `language` WHERE `name` = :language), :director, :rating, :special_features)";
			
			this.query = this.session.createQuery(hql);
			this.query.setString("title", title);
			this.query.setString("description", description);
			this.query.setLong("release_year", release_year);
			this.query.setString("language", language);
			this.query.setString("director", director);
			this.query.setString("rating", rating);
			this.query.setString("special_features", special_features);
			
			// Opening a Session using Hibernate
			this.factory = new Configuration().configure().buildSessionFactory();
			this.session = this.factory.openSession();
			
			// Execute HQL Query
			System.out.println("Query Associated: " + this.query);
			this.query.executeUpdate();
//			this.session.save(obj);
			System.out.println("Executing Query...");
			
			// Commiting the Transaction
			this.transaction.commit();
			System.out.println("Query Sucessful! Inserted 1 Row in DB.");

			// Closing Transaction Session
			this.session.close();
			
		}

		catch (Exception e) {
			
			if (this.transaction != null) 
				this.transaction.rollback();
			e.printStackTrace(); 
			return "error";
			
		}
		
		finally {
			
			System.out.println("Execution Over!");
			
		}

		return "success";

	}

	/* ####################################################################################
	#                          `editData` Execute Function                                #
	#################################################################################### */

	public String editSakilaData(FilmPojo obj) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling EditData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {

			// Opening a Session using Hibernate
	       	this.factory = new Configuration().configure().buildSessionFactory();
			this.session = this.factory.openSession();
				
			// Opening up Transaction Session
			this.transaction = this.session.beginTransaction();
			
			// HQL Query String Generation
			this.hql = "UPDATE film \r\n"
						+ "SET title = :title, \r\n"
						+ "`description` = :description,\r\n"
						+ "release_year = :release_year,\r\n"
						+ "language_id = (SELECT language_id FROM `language` WHERE `name` = :language),\r\n"
						+ "director = :director,\r\n"
						+ "rating = :rating,\r\n"
						+ "special_features = :special_features\r\n"
						+ "WHERE film_id = :film_id;";
			
			this.query = this.session.createQuery(hql);
			this.query.setString("title", obj.getTitle());
			this.query.setString("description", obj.getDescription());
			this.query.setLong("release_year", obj.getRelease_year());
			this.query.setString("language", obj.getLanguage());
			this.query.setString("director", obj.getDirector());
			this.query.setString("rating", obj.getRating());
			this.query.setString("special_features", obj.getSpecial_features());
			this.query.setInteger("film_id", obj.getFilm_id());
			
			// Execute HQL Query
			System.out.println("Query Associated: " + this.query);
			System.out.println("Executing Query...");
			this.query.executeUpdate();
			
			// Commiting the Transaction
			this.transaction.commit();
			System.out.println("Query Sucessful! Updated 1 Row in DB.");

			// Closing DB Connection
			this.session.close();
			
		}

		catch (Exception e) {
			
			if (this.transaction != null) 
				this.transaction.rollback();
			e.printStackTrace(); 
			return "error";
			
		}
		
		finally {
			
			System.out.println("Execution Over!");
			
		}

		return "success";

	}

	/* ####################################################################################
	#                         `deleteData` Execute Function                               #
	#################################################################################### */
	@SuppressWarnings("deprecation")
	public String deleteSakilaData(String del_filmIds) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling DeleteData Action...");
		System.out.println("*".repeat(50));

		// DB Connection
		try {
			

			// Opening a Session using Hibernate
	       	this.factory = new Configuration().configure().buildSessionFactory();
			this.session = this.factory.openSession();
				
			// Opening up Transaction Session
			this.transaction = this.session.beginTransaction();
			
			// Making the Request into Suitable Format
			String[] filmIdListString = del_filmIds.split(",");
			ArrayList<Integer> filmIdList = new ArrayList<>();
			for(String id : filmIdListString) {
				filmIdList.add(Integer.parseInt(id));
			}
			
			// Using For Loop to Perform the Task
					
			for(int id : filmIdList) {
							
				// HQL Query String Generation
				this.hql  = "DELETE FROM film WHERE filmId = :film_id";
				this.query = this.session.createQuery(hql);
				this.query.setInteger("film_id", id);
				
				// Execute HQL Query
				System.out.println("Query Associated: " + this.query);
				System.out.println("Executing Query...");
				this.query.executeUpdate();

			}
			
			// Commiting the Transaction
			this.transaction.commit();
			System.out.println(String.format("Query Sucessful! Deleted %d Row(s) from DB.", filmIdList.size()));
							
			// Closing Transaction Session
			this.session.close();
			
		}

		catch (Exception e) {
			
			if (this.transaction != null) 
				this.transaction.rollback();
			e.printStackTrace(); 
			return "error";
			
		}
		finally {
			
			System.out.println("Execution Over!");
			
		}

		return "success";

	}

}
