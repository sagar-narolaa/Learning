package com.sagar.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.cache.SimpleCache;
import com.sagar.dao.ProductDao;
import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired 
    private SimpleCache<Integer,Product> cache;

    public ProductServiceImpl() {
    }

    @Override
	public void saveMultiproducts(List<Product> products) {
    	products.forEach((product)-> cache.put(product.getId(), product));
    	productDao.saveAll(products);	
	}

	public ProductSearchResponseWrapper getAllproducts() {
    //	List<Product> products=cache.getAll();    	
    	return new ProductSearchResponseWrapper(entitiesToModel(productDao.findAll())) ;       
    }
	
	
	private List<ProductSearchResponse> entitiesToModel(List<Product> products) {
		System.out.println(products.size());
		return products.stream().map(product -> {
			ProductSearchResponse response = new ProductSearchResponse();
			response.setId(product.getId());
			response.setProductName(product.getName());
			response.setProductDescription(product.getDescription());
			response.setCatagoryName(product.getCatagory().getName());

			return response;
		}).collect(Collectors.toList());
	}

    public void saveproduct(Product product) {    	
    	productDao.save(product);
		Product productt=productDao.findByName(product.getName()).get();
		cache.put(productt.getId(),productt);
    }
    

    public void updateproduct(Product product) {
    	cache.put(product.getId(), product);
        productDao.save(product);       
    }

    public boolean deleteproduct(int id) {
    	cache.remove(id);
    	try {
    		productDao.deleteById(id);
    		return true;
    	}catch (Exception e) {
			return false;
    	}
    }

}
