package com.sagar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/")
	public String greet() {
		return "well hello there";		
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "well hello there User";		
	}
	

}
