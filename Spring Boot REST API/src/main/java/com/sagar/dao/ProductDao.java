package com.sagar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sagar.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query("FROM Product p WHERE p.name=?1 or p.description=?1")
	List<Product> findByAny(String any);
	
	Optional<Product> findByName(String name);

}
