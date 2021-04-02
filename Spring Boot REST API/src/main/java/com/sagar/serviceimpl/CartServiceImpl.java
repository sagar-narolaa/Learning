package com.sagar.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sagar.Utils.ObjectConverter;
import com.sagar.dao.CartDao;
import com.sagar.entity.Cart;
import com.sagar.entity.Product;
import com.sagar.model.CartProductsResponse;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.AbstractProductService;
import com.sagar.service.CartService;
import com.sagar.service.ProductService;
import com.sagar.service.UserService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao dao;

	@Autowired
	@Qualifier("MyServiceAlias")
	private AbstractProductService productService;
	@Autowired
	private UserService userService;

	@Override
	public Cart save(Cart cart) {
		return dao.save(cart);
	}

	@Override
	public List<Cart> getAll() {
		return dao.findAll();
	}

	@Override
	public Cart getById(int id) {
		return dao.findById(id).get();
	}

	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		return userName;
	}

	@Override
	@Transactional
	public void deleteProductFromCart(int productId) {		
		int cartId =userService.getUserByUserName(getUserName()).getCart().getId();
		Cart cart = dao.findById(cartId).get();
		System.out.println(cart.getId());
		Product productt = cart.getProducts().stream().filter(product -> product.getId() == productId).findAny().get();
		cart.getProducts().remove(productt);
	}

	@Override
	public CartProductsResponse getAllProducts() {	
		int cartId=userService.getUserByUserName(getUserName()).getCart().getId();
		
		List<Product> products=dao.findById(cartId).get().getProducts().stream().collect(Collectors.toList());
		
		return new CartProductsResponse(ObjectConverter.entitiesToModel(products)) ;
	}

	@Override
	@Transactional
	public Cart saveProduct(int productId) {
		
		int cartId = userService.getUserByUserName(getUserName()).getCart().getId();
		Cart cart = dao.findById(cartId).get();
		
		Product product =productService.getProduct(productId);
		cart.getProducts().add(product);
		
		return cart;
	}

}
