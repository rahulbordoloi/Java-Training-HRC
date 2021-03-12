package com.highradius.javaTraining.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
	private Criteria criteria = null;
	private Configuration config = null;
	
	// Response and Helper Variables
	private List<FilmPojo> list = new ArrayList<>();
	private List<LanguagePojo> langList = new ArrayList<LanguagePojo>();
	private boolean success = true;
	private HashMap<String, Object> responseData = new HashMap<>();
	
	// Session Factory Method
	@SuppressWarnings("deprecation")
	public SessionFactory getSession() {

		try {
			
			 this.config = new Configuration();
			 this.config.configure("hibernate.cfg.xml");
			 this.factory = this.config.buildSessionFactory();  
		
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
		
		return this.factory;
		
	}
	
	// Helper Method to Get Language Name
	public Integer getLanguageId(String languageName) {
		
		this.session =  this.getSession().openSession();
		
		// Running HQL Query
//		this.query = this.session.createQuery("SELECT language_id FROM Lang WHERE name = :language");
//		this.query.setString("start", languageName);
//		return (Integer) this.query.uniqueResult();
		
		this.query = this.session.createQuery("SELECT language_id FROM LanguagePojo WHERE name = '" + languageName + "'");
		return (Integer) this.query.uniqueResult();
		
	}
		
    /* ####################################################################################
	#                           `getData` Execute Function                                #
	#################################################################################### */
	@SuppressWarnings({ "unchecked" })
	public HashMap<String, Object> getSakilaData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling GetData Action...");
		System.out.println("*".repeat(50));
		
		// Hibernate Connectivity
		System.out.println("Checking for Hibernate Session Connection: " + this.getSession().openSession().isConnected());
		this.session = this.getSession().openSession();
		
		// Acquiring Data From Object using ORM and Adding Criterias [Filter] to our Result
		try {
			
			System.out.println("Executing Query...");
			this.criteria = this.session.createCriteria(FilmPojo.class);
			this.list = this.criteria.list();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			this.responseData.put("success", false);
			return this.responseData;
			
		} finally {
			
			// Closing the Transaction Session
			this.session.close();
			
		}
		
		// Converting the HashMap into Response
		this.responseData.put("success", true);
		this.responseData.put("totalCount", this.list.size());
		this.responseData.put("filmData", this.list);
		System.out.println("Response Prepared!");
		
		return this.responseData;
		
	}
	
    /* ####################################################################################
	#                           `geLangData` Execute Function                             #
	#################################################################################### */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getSakilaLangData() {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling GetLangData Action...");
		System.out.println("*".repeat(50));
		
		// Hibernate Connectivity
		System.out.println("Checking for Hibernate Session Connection: " + this.getSession().openSession().isConnected());
		this.session = this.getSession().openSession();
		
		// Acquiring Data From Object using ORM and Adding Criterias [Filter] to our Result
		try {
			
			System.out.println("Executing Query...");
			this.criteria = this.session.createCriteria(LanguagePojo.class);
			this.langList = this.criteria.list();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			this.responseData.put("success", false);
			return this.responseData;
			
		} finally {
			
			// Closing the Transaction Session
			this.session.close();
			
		}

		// Converting the HashMap into Response
		this.responseData.put("success", true);
//		responseData.put("lang_list", langList);
//		responseData.put("count", langList.size());
		this.responseData.put("totalCount", this.langList.size());
		this.responseData.put("lang_list", this.langList);
		System.out.println("Response Prepared!");
				
		return this.responseData;
		
	}
	
	/* ####################################################################################
	#                           `addData` Execute Function                                #
	#################################################################################### */
	public HashMap<String, Object> addSakilaData(FilmPojo obj) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling AddData Action...");
		System.out.println("*".repeat(50));
		
		// Hibernate Connectivity
		System.out.println("Checking for Hibernate Session Connection: " + this.getSession().openSession().isConnected());
		this.session = this.getSession().openSession();
		
		// ORM Operations
		try {
			
			// Acquiring Language Name from Language Model Object
			LanguagePojo langObj = (LanguagePojo) this.session.load(LanguagePojo.class, this.getLanguageId(obj.getLanguage_name())); 
			obj.setLanguage(langObj);
			
			// Executing HQL Query
			if(!this.session.getTransaction().isActive()) 
				this.session.beginTransaction();
			System.out.println("Executing Query...");
			this.session.save(obj);
			
			// Commiting the Transaction
			this.session.getTransaction().commit();
			System.out.println("Query Sucessful! Inserted 1 Row in DB.");
			success = true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			this.success = false;
			
		} finally {
			
			// Closing the Transaction Session
			this.session.close();
			
		}
		
		// Converting the HashMap into Response
		this.responseData.put("success", this.success);
		
		return this.responseData;

	}

	/* ####################################################################################
	#                          `editData` Execute Function                                #
	#################################################################################### */

	public HashMap<String, Object> editSakilaData(FilmPojo obj) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling EditData Action...");
		System.out.println("*".repeat(50));
		 
		// Hibernate Connectivity
		System.out.println("Checking for Hibernate Session Connection: " + this.getSession().openSession().isConnected());
		this.session = this.getSession().openSession();
		
		// ORM Operations
		try {
			
			// Acquiring Language Name from Language Model Object
			LanguagePojo langObj = (LanguagePojo) session.load(LanguagePojo.class, getLanguageId(obj.getLanguage_name())); 
			obj.setLanguage(langObj);
			
			// Executing HQL Query
			if(!this.session.getTransaction().isActive()) 
				this.session.beginTransaction();
			System.out.println("Executing Query...");
			this.session.saveOrUpdate(obj);
			
			// Commiting the Transaction
			this.session.getTransaction().commit();
			System.out.println("Query Sucessful! Updated 1 Row in DB.");
			this.success = true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			this.success = false;
			
		} finally {
			
			// Closing the Transaction Session
			this.session.close();
			
		}
		
		// Converting the HashMap into Response
		this.responseData.put("success", this.success);
		
		return this.responseData;

	}

	/* ####################################################################################
	#                         `deleteData` Execute Function                               #
	#################################################################################### */
	@SuppressWarnings("deprecation")
	public HashMap<String, Object> deleteSakilaData(String del_filmIds) {
		
		System.out.println("*".repeat(50));
		System.out.println("Calling DeleteData Action...");
		System.out.println("*".repeat(50));
		
		// Hibernate Connectivity
		System.out.println("Checking for Hibernate Session Connection: " + this.getSession().openSession().isConnected());
		this.session = this.getSession().openSession();
		
		// Making the Request into Suitable Format
		String[] filmIdListString = del_filmIds.split(",");
		ArrayList<Integer> filmIdList = new ArrayList<>();
		for(String id : filmIdListString) {
			filmIdList.add(Integer.parseInt(id));
		}
		
		// ORM Operations
		try {
			
			// Opening up Transaction Session
			if(!this.session.getTransaction().isActive()) 
				this.transaction = this.session.beginTransaction();
			
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
			
		} catch (Exception e) {
			
			if (this.transaction != null) 
				this.transaction.rollback();
			e.printStackTrace(); 
			this.success = false;
			
		} finally {
			
			// Closing the Transaction Session
			this.session.close();
			
		}
		
		// Converting the HashMap into Response
		this.responseData.put("success", this.success);
		
		return this.responseData;

	}

}
