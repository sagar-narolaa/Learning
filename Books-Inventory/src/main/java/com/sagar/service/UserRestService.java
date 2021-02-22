package com.sagar.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.dao.UserRestJpaDao;
import com.sagar.model.User;

@Service
@Transactional
public class UserRestService {

	@Autowired
	private UserRestJpaDao userDao;

	public List<User> getAllUsers() {
		System.out.println("============service called=========");
		return userDao.findAll();
	}
	
	
	public List<User> saveUser(User user) {
		System.out.println("============service called=========");
		userDao.save(user);
		return getAllUsers();
	}
	
	
	public List<User> updateUser(User user) {
		System.out.println("============service called=========");
		userDao.update(user);
		return getAllUsers();
	}
	

	public List<User> deleteUser(int id) {
		System.out.println("============service called=========");
		userDao.delete(id);
		return getAllUsers();
	}

}
