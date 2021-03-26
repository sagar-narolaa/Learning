package com.sagar.model;

import java.io.Serializable;

public class ProductSearchResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productId;
	private String productName;
	private String productDescription;
	private int categoryId;
	private String categoryName;



	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public ProductSearchResponse(int productId, String productName, String productDescription, int categoryId,
			String categoryName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public ProductSearchResponse() {
		super();
	}



}
