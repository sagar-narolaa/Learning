package com.sagar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.Cart;
import com.sagar.model.CartProductsResponse;
import com.sagar.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@PostMapping
	public Cart createNewCart(@RequestBody Cart cart) {
		return cartService.save(cart);
	}
	
	@PostMapping("_saveProduct")
	public Cart saveProduct(@RequestParam("id") int id) {
		return cartService.saveProduct(id);
	}

	@GetMapping
	public Cart getCartById(@RequestParam("id") int id) {
		return cartService.getById(id);
	}

	@GetMapping("/_getProducts")
	public CartProductsResponse getProducts() {
		return cartService.getAllProducts();
	}
		
	@DeleteMapping
	public void deleteProduct(@RequestParam("pId") int productId) {
		cartService.deleteProductFromCart(productId);
	}

}
