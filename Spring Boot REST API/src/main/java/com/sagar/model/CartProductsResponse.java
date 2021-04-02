package com.sagar.model;

import java.util.ArrayList;
import java.util.List;

public class CartProductsResponse {
	private int count;
	private List<ProductSearchResponse> products = new ArrayList<>();

	public CartProductsResponse(List<ProductSearchResponse> products) {
		this.products = products;
		this.count=products.size();
	}

	public List<ProductSearchResponse> getProducts() {
		return products;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setProducts(List<ProductSearchResponse> products) {
		this.products = products;
		this.count=products.size();
	}
}
