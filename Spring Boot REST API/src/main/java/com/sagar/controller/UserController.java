package com.sagar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.model.LoginRequest;
import com.sagar.model.LoginResponse;
import com.sagar.model.UserSignup;
import com.sagar.service.UserService;
import com.sagar.springSecurity.JwtUtil;
import com.sagar.springSecurity.UserDetailsServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager; 

	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	

	@PostMapping
	public void signup(@RequestBody UserSignup userInfo) {
		userService.signup(userInfo);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> login(@RequestBody LoginRequest userInfo) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(userInfo.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);
		
		System.out.println(jwt);
		return ResponseEntity.ok(new LoginResponse(jwt));
	}
	
	@DeleteMapping
	public void delete(@RequestParam("id") int id) {
		userService.delete(id);
	}

}
