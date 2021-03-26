package com.sagar.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.dao.ProductDao;
import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponse;
import com.sagar.service.ProductService;

@Service
public abstract class AbstractProductService implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public List<ProductSearchResponse> getAllproducts() {
		System.out.println("in super getall users");
		return dao.findAllProducts();
	}

	@Override
	public Product saveproduct(Product product) {
		return dao.save(product);
	}

	@Override
	public Product updateproduct(Product product) {
		return dao.save(product);
	}

	@Override
	public boolean deleteproduct(int id) {
		dao.deleteById(id);
		return true;
	}

	@Override
	public void saveMultiproducts(List<Product> products) {
		dao.saveAll(products);

	}

	@Override
	public Product getProduct(int id) {
		return dao.findById(id).get();
	}

}
