package com.sagar.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sagar.entity.UserEntity;
import com.sagar.model.Book;
import com.sagar.model.User;

@Repository("UserDaoJPA")
public class UserDaoJPA implements UserDao {
	
	@PersistenceContext
	EntityManager em;


	@Override
	public int signIn(String email, String pwd) {
		User user=em.find(User.class,email);
			if(user!=null) {
			if(user.getEmail().equals(email)  && user.getPwd().equals(pwd)) {
				return 1;
			}else if (user.getEmail().equals(email) || user.getPwd().equals(pwd)) {
				return 0;
			}
		}
			return -1;
	}

	@Override
	public boolean signUp(UserEntity user) {
		
		em.persist(user);
		return true;
		/*
		 * User user=em.find(User.class,email); if(user!=null){ return false; }else {
		 * 
		 * em.persist(userr); return true; }
		 */
		
	}

	@Override
	public boolean signUp(String fname, String lname, String email, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}


}
