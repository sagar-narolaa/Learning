
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
		Query query = em.createQuery("from User user where user.email=:email AND user.pwd=:pwd");
		query.setParameter("email",email);
		query.setParameter("pwd",pwd);
		User user1=(User) query.getSingleResult();
		System.out.println(user1.getUserId());
		User user = em.find(User.class,user1.getUserId());
		
		if (user != null) {
			if (user.getEmail().equals(email) && user.getPwd().equals(pwd)) {
				return 1;
			} else if (user.getEmail().equals(email) || user.getPwd().equals(pwd)) {
				return 0;
			}
		}
		return -1;
	}

	@Override
	public boolean signUp(UserEntity user) {

		em.persist(user);
		return true;

	}

	@Override
	public boolean signUp(String fname, String lname, String email, String pwd) {
		return false;
	}

}
