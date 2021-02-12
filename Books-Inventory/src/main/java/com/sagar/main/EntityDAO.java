package com.sagar.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sagar.entity.City;



public class EntityDAO {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Book_Details");
	static EntityManager em = emf.createEntityManager();
	
	public static City fetchCity(String cityName) {
		em.getTransaction().begin();
		Query query=null;
		query = em.createQuery(" from City city where city.cityName=:name");
		query.setParameter("name", cityName);		
		City city=(City) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		return city;
	}
	
	


}
