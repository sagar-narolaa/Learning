package com.sagar.service;

import java.util.List;
import java.util.Set;

import com.sagar.entity.Cart;
import com.sagar.entity.Product;
import com.sagar.model.CartProductsResponse;
import com.sagar.model.ProductSearchResponseWrapper;

public interface CartService {

	public List<Cart> getAll();

	public Cart getById(int id);

	public void deleteProductFromCart(int productId);

	Cart save(Cart cart);

	public CartProductsResponse getAllProducts();

	public Cart saveProduct(int id);

}
