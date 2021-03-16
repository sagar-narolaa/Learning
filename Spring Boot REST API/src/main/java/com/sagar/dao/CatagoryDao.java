package com.sagar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sagar.entity.Catagory;

@Repository
public interface CatagoryDao extends JpaRepository<Catagory, Integer> {
	
    

    @Query("FROM Catagory u WHERE u.name=?1")
    List<Catagory> findByAny(String any);

    Optional<Catagory> findByName(String name);
}