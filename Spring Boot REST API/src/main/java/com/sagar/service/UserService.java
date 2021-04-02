package com.sagar.service;

import com.sagar.entity.User;
import com.sagar.model.UserSignup;

public interface UserService {


	void signup(UserSignup req);
	
	User getUserByUserName(String userName);

	void delete(int id);

}