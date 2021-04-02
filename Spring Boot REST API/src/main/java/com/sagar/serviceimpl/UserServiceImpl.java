package com.sagar.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sagar.dao.UserRepository;
import com.sagar.entity.Cart;
import com.sagar.entity.User;
import com.sagar.model.UserSignup;
import com.sagar.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository dao;	
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public User getUserByUserName(String userName) {
		return dao.getUserByUsername(userName);
	}

	@Override
	public void signup(UserSignup req) {

		User user = new User();
		user.setUsername(req.getUsername());
		user.setEnabled(req.isEnabled());
		//user.setPassword(req.getPassword());
		user.setPassword(pwdEncoder.encode(req.getPassword()));
		user.setRoles(req.getRoles());

		Cart cart = new Cart();
		cart.setProducts(null);
		cart.setUser(user);

		user.setCart(cart);
		dao.save(user);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);		
	}

}
