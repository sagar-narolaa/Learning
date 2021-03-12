package com.sagar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sagar.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
    List<User> findByLname(String lname);

    @Query("FROM User u WHERE u.fname=?1 or u.lname=?1 or u.email=?1")
    List<User> findByAny(String any);

    Optional<User> findByFname(String fname);
}