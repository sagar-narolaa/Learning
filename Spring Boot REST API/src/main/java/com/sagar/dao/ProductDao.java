package com.sagar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponse;

public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query("FROM Product p WHERE p.name=?1 or p.description=?1")
	List<Product> findByAny(String any);
	
	Optional<Product> findByName(String name);
		
	@Query("select new com.sagar.model.ProductSearchResponse(p.id,p.name,p.description,c.id ,c.name) FROM Product p inner join p.catagory c")
	List<ProductSearchResponse> findAllProducts(); 

	/*
	 * @Query("select new com.example.InventoryManager.narola.Model.ProductsModel(p.productId,p.productName,p.description, c.catogId,c.catogName) FROM Products p inner join p.category c"
	 * ) List<ProductsModel> findAllProjectsWithCategories();
	 */

}
