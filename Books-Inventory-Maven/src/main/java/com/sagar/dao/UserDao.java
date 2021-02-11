package com.sagar.dao;

import com.sagar.entity.UserEntity;

public interface UserDao {
	
	public int signIn(String email, String pwd);
	public boolean signUp(String fname, String lname, String email, String pwd);
	boolean signUp(UserEntity user);

}
