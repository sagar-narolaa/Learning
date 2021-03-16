package com.sagar.model;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchResponseWrapper {
	/*
	 * private int count;
	 * 
	 * public int getCount() { return count; }
	 * 
	 * public void setCount() {
	 * 
	 * this.count = products.size(); }
	 */

	private List<ProductSearchResponse> products = new ArrayList<>();

	private ProductSearchResponseWrapper() {
	}

	public ProductSearchResponseWrapper(List<ProductSearchResponse> products) {
		this.products = products;
		//this.count=products.size();
	}

	public List<ProductSearchResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSearchResponse> products) {
		this.products = products;
	}

}