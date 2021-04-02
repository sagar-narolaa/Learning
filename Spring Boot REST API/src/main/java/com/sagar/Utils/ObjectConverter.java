package com.sagar.Utils;

import java.util.List;
import java.util.stream.Collectors;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponse;

public class ObjectConverter {
	
	
	public static List<ProductSearchResponse> entitiesToModel(List<Product> products) {
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

}
