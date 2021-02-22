
package com.sagar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.stereotype.Repository;

import com.sagar.model.User;

@Repository("userRestJPA")
public class UserRestJpaDao {

	@PersistenceContext
	EntityManager em;

	public List<User> findAll() {
		System.out.println("================Into the Find all=============");
		Query query = em.createQuery("from User");
		List<User> users = query.getResultList();
		return users;
	}

	public void save(User user) { // em.getTransaction().begin();
		em.persist(user); // em.getTransaction().commit();
	}

	public void update(User user) {
		User existingUser = em.find(User.class, user.getUserId()); // em.getTransaction().begin();
		System.out.println("Update transcation started");
		System.out.println("========================================="+existingUser.getFname()+"=======================");
		
		existingUser.setFname(user.getFname());
		existingUser.setLname(user.getLname());
		existingUser.setEmail(user.getEmail());
		
		System.out.println("Update Succesfull"); // em.getTransaction().commit();
	}

	public void delete(int id) {
		User user = em.find(User.class, id); 		
		em.remove(user); 
		
	}

	public User findById(int id) {
		return em.find(User.class, id);
	}

}
