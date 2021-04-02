package com.sagar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.model.UserSignup;
import com.sagar.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String greet() {
		return "well hello there";
	}

	@GetMapping("/user")
	public String getUser() {
		return "well hello there User";
	}

	@PostMapping
	public void signup(@RequestBody UserSignup userInfo) {
		userService.signup(userInfo);
	}
	
	@DeleteMapping
	public void delete(@RequestParam("id") int id) {
		userService.delete(id);
	}

}
