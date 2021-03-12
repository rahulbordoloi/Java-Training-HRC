package org.highradius.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.highradius.model.Movie;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.highradius.model.Lang;

public class MoviesDao {
	
	public SessionFactory getSession() {
		SessionFactory factory = null;
		try {
			 Configuration cfg=new Configuration();
			 cfg.configure("hibernate.cfg.xml");
			 factory=cfg.buildSessionFactory();
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		return factory;
	}
	
	public Integer getLangId(String name) {
		Session session = getSession().openSession();
		Query query = session.createQuery("select language_id from Lang where name = '"+name+"'");
		return (Integer)query.uniqueResult();
	}
	
	public HashMap<String, Object> loadData(){
		System.out.println(getSession().openSession().isConnected());
		Session session = getSession().openSession();
		List<Movie> list = new ArrayList<Movie>();
		try {
			Criteria criteria = session.createCriteria(Movie.class);
			criteria.add(Restrictions.eq("isDeleted", false));
			
			list = criteria.list();
		}
		catch(Exception e) {
			
		}
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("movie_list",list);
		output.put("count",list.size());
		
		return output;
	}
	
	
	public HashMap<String, Object> langData(){
		
		List<Lang> list = new ArrayList<Lang>();
		System.out.println(getSession().openSession().isConnected());
		Session session = getSession().openSession();
		
		try {
			Criteria criteria = session.createCriteria(Lang.class);
			
			list = criteria.list();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("lang_list",list);
		output.put("count",list.size());
		
		return output;
	}
	
	
	public HashMap<String, Object> editData(Movie ob){
		
		boolean success ;
//		if(ob.getDescription() == "")
//			ob.setDescription(null);
//		if(ob.getRating() == "")
//			ob.setRating(null);
//		if(ob.getSpecial_features() == "")
//			ob.setSpecial_features(null);
//		else
//			ob.setSpecial_features(ob.getSpecial_features().replaceAll("[,]\\s+", ","));
//		if(ob.getRelease_year() == "")
//			ob.setRelease_year(null);
		 
		Session session = getSession().openSession();
		
		try {
			Lang obj = session.load(Lang.class,getLangId(ob.getLanguage_name())); 
			ob.setLanguage(obj);
			if(!session.getTransaction().isActive()) 
				session.beginTransaction();
			session.saveOrUpdate(ob);
			session.getTransaction().commit();
			
			System.out.println("edited");
			success = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("success",success);
		
		return output;
	}
	
	
	public HashMap<String, Object> deleteData(String ids){
		boolean success;
		try {
			Session session = getSession().openSession();
			
			String sql = "UPDATE Movie SET isDeleted = 1 WHERE film_id IN ("+ids+")";
	
			if(!session.getTransaction().isActive()) 
				session.beginTransaction();
			session.createQuery(sql).executeUpdate();
			session.getTransaction().commit();
			
			System.out.println("del");
			success = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("success",success);
		
		return output;
	}
		
	public HashMap<String, Object> addData(Movie ob){
		boolean success;
		
//		if(ob.getDescription() == "")
//			ob.setDescription(null);
//		if(ob.getRating() == "")
//			ob.setRating(null);
//		if(ob.getSpecial_features() == "")
//			ob.setSpecial_features(null);
//		else
//			ob.setSpecial_features(ob.getSpecial_features().replaceAll("[,]\\s+", ","));
//		if(ob.getRelease_year() == "")
//			ob.setRelease_year(null);
		
		Session session = getSession().openSession();
		
		try {
			Lang obj = session.load(Lang.class,getLangId(ob.getLanguage_name())); 
			ob.setLanguage(obj);
			
			if(!session.getTransaction().isActive()) 
				session.beginTransaction();
			session.save(ob);
			session.getTransaction().commit();
			System.out.println("add");
			success = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			success = false;
		}
		 
		 HashMap<String, Object> output = new HashMap<String, Object>();
		 output.put("success",success);
			
		 return output;
	}
}
