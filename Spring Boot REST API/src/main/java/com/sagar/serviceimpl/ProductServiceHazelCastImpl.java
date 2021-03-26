
package com.sagar.serviceimpl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.AbstractProductService;

@Service("hazelCast")
public class ProductServiceHazelCastImpl extends AbstractProductService {

	public ProductServiceHazelCastImpl() {
	}
	
	public ProductSearchResponseWrapper searchProduct(ProductSearchRequest request) {
		return super.searchProduct(request);
	}

	@Override
	@Cacheable
	public Product getProduct(int id) {
		return super.getProduct(id);
	}

	@Override
	public void saveMultiproducts(List<Product> products) {
		super.saveMultiproducts(products);
	}

	@Cacheable(value = "hazelcast")
	public List<ProductSearchResponse> getAllproducts() {
		return super.getAllproducts();
	}

	// @CachePut(value = "productsCache", key = "#product.id")

	@CacheEvict
	public Product saveproduct(Product product) {
		return super.saveproduct(product);
	}

	@CacheEvict(value = "hazelcast", key = "#product.id")
	public Product updateproduct(Product product) {

		return super.updateproduct(product);
	}

	@CacheEvict
	public boolean deleteproduct(int id) {
		try {
			super.deleteproduct(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
