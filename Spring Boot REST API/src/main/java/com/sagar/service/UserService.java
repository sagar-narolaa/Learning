package com.sagar.service;

import java.util.List;
import java.util.Optional;

import com.sagar.entity.User;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> saveUser(User user);
    
    void updateUser(User user);
    
    
    List<User> usersWithPageAndSort(int pageIndex,int size, String order, String... sortBy);
    	
    void deleteMultipleUsers(List<User> users);
    
    void saveMultipleUsers(List<User> users);
    
    List<User> findByAnytg(String any);
    
    List<User> usersWithPagination(int index, int size);

    Optional<User> findByFname(String fname);

    List<User> findByLname(String lname);

	User getUserByAny(String any);

	User getUserById(int id);

	void deleteAllUsers();

	void deleteUser(int id);
}
