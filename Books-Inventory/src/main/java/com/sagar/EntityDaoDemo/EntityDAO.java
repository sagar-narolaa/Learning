package com.sagar.EntityDaoDemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sagar.entity.City;
import com.sagar.entity.Country;

public class EntityDAO {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Book_Details");
	static EntityManager em = emf.createEntityManager();
	
	public static City fetchCity(String city) {
		em.getTransaction().begin();
		Query query=null;
		query = em.createQuery(" from City city where city.cityName=:cityName");
		query.setParameter("cityName", city);		
		City city1=(City) query.getSingleResult();
		
		em.getTransaction().commit();
		return city1;
	}

	public static Country getCountry(String country) {
		em.getTransaction().begin();
		Query query=null;
		query = em.createQuery(" from Country country where country.name=:countryName");
		query.setParameter("countryName", country);		
		Country country1=(Country)query.getSingleResult();		
		
		em.getTransaction().commit();
		
		return country1;
	}
	
	
	
}
