package com.highradius.training.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import com.highradius.training.dao.daoInterface;

import com.highradius.training.Model.Sakila_pojo;
import com.highradius.training.Model.ActualPojo;
import com.highradius.training.Model.LanguagePojo ;
public class daoImplementation implements daoInterface {
	private static SessionFactory factory;
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> dataFetch(int start,int limit) {
		Map<String,Object> map=new HashMap<String,Object>();
		factory = new Configuration().configure().buildSessionFactory();
		   Session session = factory.openSession();
		   Transaction tx = null;
		   List response =null;
		   List<ActualPojo> obj = new ArrayList<ActualPojo>();
		   try {
				tx = session.beginTransaction();
				Criteria cr = session.createCriteria(Sakila_pojo.class);
				System.out.println(Sakila_pojo.class);
				//cr.setFirstResult(start);
				//cr.setMaxResults(limit);
				cr.addOrder(Order.asc("filmId"));
				response = cr.list();
				System.out.println(response);
				 for (Iterator iterator = response.iterator(); iterator.hasNext();){
					 Sakila_pojo filmObj = (Sakila_pojo) iterator.next();
					 ActualPojo  pojoobj = new ActualPojo();
					 pojoobj.setFilm_id(filmObj.getFilmId());
					 pojoobj.setDescription(filmObj.getDescription());
					 pojoobj.setDirector(filmObj.getDirector());
					 pojoobj.setLanguageName(filmObj.getLanguageName().getName());
					 pojoobj.setRating(filmObj.getRating());
					 pojoobj.setRealeaseYear(filmObj.getRealeaseYear());
					 pojoobj.setTitle(filmObj.getTitle());
					 pojoobj.setSpecialFeatures(filmObj.getSpecialFeatures());
					 obj.add(pojoobj);
				 }
				tx.commit();
				
			} 
			catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} 
			finally {
				session.close(); 
			}
	   		   map.put("data", obj);
		       map.put("totalCount",limit);
	   	 return map;
	 }
		   



	@Override
	public void dataAdd(String title, String director, Integer year, String language, String description,
			String rating, String specialFeatures) {
		// TODO Auto-generated method stub
		System.out.println(title+director+language);
		String movieaddForm = (title!=null && !title.isEmpty())?title:"";
	 	   movieaddForm = movieaddForm.toUpperCase();
	 	   String DirectorNameaddForm = (director!=null && !director.isEmpty())?director:"";
	 	   Integer releaseYearaddForm = (year!=null)?year:0;
	 	   String LanguageNameaddForm = (language!=null && !language.isEmpty())?language:"";
	 	   String DescriptionaddForm = (description!=null && !description.isEmpty())?description:"";
	 	   String RatingaddForm = (rating!=null && !rating.isEmpty())?rating:"";
	 	   String specialFeaturesaddForm = (specialFeatures!=null && !specialFeatures.isEmpty())?specialFeatures:"";	
	 	   if(!specialFeaturesaddForm.equals("")) {
	 		  specialFeaturesaddForm = specialFeaturesaddForm.replace(", ", ",");	
	 	   }
	 		 factory = new Configuration().configure().buildSessionFactory();
			   Session session = factory.openSession();
			   Transaction tx = null;
	 	  try {
	 		 tx=session.beginTransaction();
	 		String hql = "FROM LanguagePojo l WHERE l.name = :language";
	 		Query query = session.createQuery(hql);
			query.setParameter("language", LanguageNameaddForm);
			
			List response = query.list();
			
			LanguagePojo langObj = null;
				 for (Iterator iterator = response.iterator(); iterator.hasNext();){
					 langObj = (LanguagePojo) iterator.next();
				 }
				 System.out.println(langObj);
				Timestamp instant= Timestamp.from(Instant.now());  
				Sakila_pojo obj = new Sakila_pojo();
				obj.setTitle(movieaddForm);
				obj.setDirector(DirectorNameaddForm);
				obj.setRealeaseYear(releaseYearaddForm);
				obj.setLanguageName(langObj);
				obj.setDescription(DescriptionaddForm);
				obj.setRating(RatingaddForm);
				obj.setSpecialFeatures(specialFeaturesaddForm);
				obj.setRentalDuration(0);
				obj.setRentalRate(0.0f);
				obj.setReplacementCost(0.0f);
				obj.setLastUpdate(instant.toString());
				session.save(obj);
				tx.commit();
				
			} 
			catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} 
			finally {
				session.close(); 
			}
	}

	@Override
	public void dataEdit(Integer filmId, String title, String director, Integer releaseYear, String language,
			String description, String rating) {
		// TODO Auto-generated method stub
		
			
			System.out.println("Start");
			final String JDBC_Driver = "com.mysql.jdbc.Driver";  
		    final String dbURL="jdbc:mysql://localhost:3306/sakila";
		    final String userId = "root";
		    final String password = "root";
	
		    System.out.println("Her editing will be done");
		    int rs=0;
			
		    PreparedStatement pstmt=null;
		    Connection conn=null;
		   
		    try {
				Class.forName(JDBC_Driver);
				conn = DriverManager.getConnection(dbURL, userId, password);
				System.out.println("Connection Done...");
				String sql="UPDATE film SET title=?,director=?,release_year=?,language_id=(select language_id from language where name=?),rating=?,description=? WHERE film_id=? ";		    	
				
	  
	    		System.out.println(title+" "+filmId+" "+description);
		    	pstmt = conn.prepareStatement(sql);
		    	Integer filmId1 = filmId;
		    	String title1 = (title!=null && !title.isEmpty())?title:"";
		    	title1 = title1.toUpperCase();
			 	   String director1 = (director!=null && !director.isEmpty())?director:"";
			 	   Integer releaseYear1 = (releaseYear!=null )?releaseYear:0;
			 	   String language1 = (language!=null && !language.isEmpty())?language:"";
			 	   String description1 = (description!=null && !description.isEmpty())?description:"";
			 	   String rating1 = (rating!=null && !rating.isEmpty())?rating:"";
		    	pstmt.setString(1,title1);
		    	pstmt.setString(2,director1);
		    	pstmt.setInt(3,releaseYear1);
		    	pstmt.setString(4,language1);
		    	pstmt.setString(5,rating1);
		    	pstmt.setString(6,description1);
		    	pstmt.setInt(7,filmId1);
		    	rs = pstmt.executeUpdate();
		    	
		    	System.out.println("Execution of query DONE..");
		    
		    
		    	
		    	
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		       	 try {
			       		conn.close();
			       		
			       		pstmt.close();
			       		
			       		
							 System.out.println("Closed");
						} catch (Exception e) {
							
							e.printStackTrace();
						}
			}
			
	}

	@Override
	public void dataDelete(Integer filmId) {
		// TODO Auto-generated method stub
		
			
		Integer film_id = filmId;
       	factory = new Configuration().configure().buildSessionFactory();
		   Session session = factory.openSession();
		   Transaction tx = null;
		   
		   
			try {
				tx = session.beginTransaction();
				String hql = "DELETE FROM film WHERE filmId = :film_id";
				Query query = session.createQuery(hql);
				
				query.setParameter("film_id", film_id);
				query.executeUpdate();
				tx.commit();
			} 
			catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} 
			finally {
				session.close(); 
			}
	}	

}
