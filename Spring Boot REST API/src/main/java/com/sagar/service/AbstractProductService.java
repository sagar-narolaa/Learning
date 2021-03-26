package com.sagar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.Utils.ObjectConverter;
import com.sagar.dao.ProductDao;
import com.sagar.dao.ProductDaoCriteria;
import com.sagar.entity.Product;
import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;

@Service
public abstract class AbstractProductService implements ProductService {

	@Autowired
	private ProductDao dao;

	@Autowired
	ProductDaoCriteria productDao;

	public List<Product> findProductsBySearch(ProductSearchRequest request) {

		List<Product> products = productDao.getproductsWithSearch(request.getSortBy(), request.getSortOrder(),
				request.getRecordsPerPage(), request.getSearch(), request.getCatagoryId(), request.getPageIndex());

		return products;
	}

	@Override
	public ProductSearchResponseWrapper searchProduct(ProductSearchRequest request) {
		return new ProductSearchResponseWrapper(ObjectConverter.entitiesToModel(findProductsBySearch(request)));
	}

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
