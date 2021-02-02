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

import com.sagar.Service.Authentication_Service;
import com.sagar.model.User;


@Controller
public class UserController {	
	@Autowired
	Authentication_Service authentication_Service;

    @GetMapping("/")
    private String userLoginPage() throws IOException {
    	return "userLogin";
    }
    
    @GetMapping("/signupPage")
    private String signupPage() throws  IOException {
    	return "userSignUp";
	}
    
    @GetMapping("/logout")
	private String logout(HttpSession session) throws IOException {		
		session.invalidate();		
		return "userLogin"; 
    }
    
    @PostMapping("/signup")
    private String signup(@ModelAttribute User user,Model model) throws IOException, ServletException {
    	
    	boolean signup_status=authentication_Service.signUp(user,model);    	
    	model.addAttribute("signup_status",signup_status);
    	return "userLogin"; 		
   	
    }
    
  
    @PostMapping("/login")
    public String login(@RequestParam("email") String Email,@RequestParam("pwd") String Pwd,Model model) throws IOException, SQLException, ServletException {
    	
    	int status=authentication_Service.login(Email,Pwd,model);
    	
    	if(status==1) {			  
			  return "redirect:/list";
		  }else { 
			  model.addAttribute("status",status);
			  return "userLogin";
			  }	
    }

}
