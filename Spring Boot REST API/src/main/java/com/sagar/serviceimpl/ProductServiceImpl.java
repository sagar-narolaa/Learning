package com.sagar.serviceimpl;

import org.springframework.stereotype.Service;

import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.AbstractProductService;

@Service("noCache")

public class ProductServiceImpl extends AbstractProductService {
	
	

	@Override
	public ProductSearchResponseWrapper searchProduct(ProductSearchRequest request) {
		return super.searchProduct(request);
	}
}
