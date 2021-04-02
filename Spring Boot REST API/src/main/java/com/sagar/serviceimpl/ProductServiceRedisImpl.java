package com.sagar.serviceimpl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.AbstractProductService;

@Service("redis")
public class ProductServiceRedisImpl extends AbstractProductService {

	public ProductServiceRedisImpl() {
	}

	@Override
	public ProductSearchResponseWrapper searchProduct(ProductSearchRequest request) {
		return super.searchProduct(request);
	}

	@Override
	@Cacheable(value = "products", key = "#id")
	public Product getProduct(int id) {
		return super.getProduct(id);
	}

	@Override
	public void saveMultiproducts(List<Product> products) {
		super.saveMultiproducts(products);
	}

	@Cacheable(value = "products")
	public List<ProductSearchResponse> getAllproducts() {
		return super.getAllproducts();
	}

	// @CachePut(value = "productsCache", key = "#product.id")
	@CacheEvict(value = "products", key = "#product.id", allEntries = true)
	public Product saveproduct(Product product) {
		return super.saveproduct(product);
	}

	@CacheEvict(value = "products", key = "#product.id", allEntries = true)
	// @CachePut(value = "productsCache", key = "#product.id")
	public Product updateproduct(Product product) {
		/*
		 * Cache cache = cm.getCache("productsCache"); cache.evict(product.getId());
		 * cache.put(product.getId(), product);
		 */

		return super.updateproduct(product);
	}

	@CacheEvict(value = "products", key = "#id", allEntries = true)
	public boolean deleteproduct(int id) {
		try {
			super.deleteproduct(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
