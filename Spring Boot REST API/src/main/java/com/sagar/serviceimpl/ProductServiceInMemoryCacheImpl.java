package com.sagar.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.Utils.ObjectConverter;
import com.sagar.cache.SimpleCache;
import com.sagar.entity.Catagory;
import com.sagar.entity.Product;
import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.AbstractProductService;

@Service("inMemory")
public class ProductServiceInMemoryCacheImpl extends AbstractProductService {

	@Autowired
	private SimpleCache<Integer, Product> cache;

	public ProductServiceInMemoryCacheImpl() {
	}
	
	
	@Override
	public ProductSearchResponseWrapper searchProduct(ProductSearchRequest request) {
		return super.searchProduct(request);
	}
	
	@Override
	public Product getProduct(int id) {
		return cache.get(id).get();
		// return productDao.findById(id).get();
	}

	@Override
	public void saveMultiproducts(List<Product> products) {
		products.forEach((product) -> cache.put(product.getId(), product));
		super.saveMultiproducts(products);
	}

	public List<ProductSearchResponse> getAllproducts() {
		List<Product> products = cache.getAll();
		if (products.size() == 0) {
			List<ProductSearchResponse> list = super.getAllproducts();
			list.forEach(product -> {

				Catagory c = new Catagory();
				c.setId(product.getCategoryId());
				c.setName(product.getCategoryName());

				Product p = new Product();
				p.setId(product.getProductId());
				p.setName(product.getProductName());
				p.setDescription(product.getProductDescription());
				p.setCatagory(c);

				cache.put(p.getId(), p);
			});
		}

		return entitiesToModel(cache.getAll());

	}

	protected List<ProductSearchResponse> entitiesToModel(List<Product> products) {
		return products.stream().map(product -> {
			ProductSearchResponse response = new ProductSearchResponse();
			response.setProductId(product.getId());
			response.setProductName(product.getName());
			response.setProductDescription(product.getDescription());
			response.setCategoryId(product.getCatagory().getId());
			response.setCategoryName(product.getCatagory().getName());
			return response;
		}).collect(Collectors.toList());
	}

	public Product saveproduct(Product product) {
		cache.put(product.getId(), product);
		return super.saveproduct(product);
	}

	public Product updateproduct(Product product) {
		cache.put(product.getId(), product);
		return super.saveproduct(product);
	}

	public boolean deleteproduct(int id) {

		try {
			super.deleteproduct(id);
			cache.remove(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
