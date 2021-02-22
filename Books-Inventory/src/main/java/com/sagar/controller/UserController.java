
package com.sagar.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagar.entity.UserEntity;
import com.sagar.model.User;
import com.sagar.propertiesLoader.PropertiesLoader;
import com.sagar.service.AuthenticationService;

@Controller
public class UserController {

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/")
	private String userLoginPage() throws IOException {
		return "userLogin";
	}

	@GetMapping("/signupPage")
	private String signupPage() throws IOException {
		return "userSignUp";
	}

	@GetMapping("/logout")
	private String logout() throws IOException {
		return "userLogin";
	}

	@PostMapping("/signup")
	private String signup(@ModelAttribute UserEntity user, Model model) throws IOException, ServletException {

		boolean signup_status = authenticationService.signUp(user, model);
		model.addAttribute("signup_status", signup_status);
		return "userLogin";

	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, Model model)
			throws IOException, SQLException, ServletException {

		int status = authenticationService.login(email, pwd);

		if (status == 1) {
			return "redirect:/list";
		} else {
			model.addAttribute("status", status);
			return "userLogin";
		}
	}

}
