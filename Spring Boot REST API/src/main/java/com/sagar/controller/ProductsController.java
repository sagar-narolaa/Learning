package com.sagar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponse;
import com.sagar.service.ProductService;

@RestController
@RequestMapping({ "/products" })

public class ProductsController {
	@Autowired
	@Qualifier("serviceWithEHcache")
	private ProductService productService;

	@GetMapping("/list")
	public List<ProductSearchResponse> getAllProducts() {
		return productService.getAllproducts();
	}

	@GetMapping
	public Product getProduct(@RequestParam("id") int id) {
		return productService.getProduct(id);
	}
	

	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveproduct(product);
		
	}

	@PostMapping({ "/saveMulti" })
	public List<ProductSearchResponse> saveMultipleProducts(@RequestBody List<Product> products) {
		productService.saveMultiproducts(products);
		return getAllProducts();
	}

	@PutMapping
	public Product update(@RequestBody Product product) {
		return productService.updateproduct(product);
	}

	@DeleteMapping
	public boolean delete(@RequestParam("id") int id) {
		return productService.deleteproduct(id);
	}

}