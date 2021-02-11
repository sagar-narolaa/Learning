package com.sagar.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sagar.entity.Address;
import com.sagar.entity.City;
import com.sagar.entity.Country;
import com.sagar.entity.State;
import com.sagar.entity.UserEntity;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Book_Details");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		UserEntity user = new UserEntity();
		user.setFname("123123123123132");
		user.setEmail("sagasdae32424dssada43q43asd@gmail.com");
		user.setLname("r3w43242342342342342olanki hehe");
		user.setPwd("admin@1234");
		
		City city = new City();
		city.setCityName("Suratiiiiiiiiiiiiiii");

		State state = new State();
		state.setStateName("Gujaratiiiiiiiiiiii");

		Country country = new Country();
		country.setName("India");
		
		city.setState(state);
		state.setCountry(country);
		 
		Address addr1 = new Address();
		addr1.setAddrLine1("12 house numner,smtg society");
		addr1.setAddrLine2("Behind school");
		addr1.setAddrLine3("Jalalpore");
		addr1.setAddrType("home");
		addr1.setPincode("396421");
		addr1.setUser(user);
		addr1.setCity(city);	
		addr1.setCountry(country);
		addr1.setState(state);		 
	
		user.getAddrList().add(addr1);		 		

		em.persist(user);	

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
