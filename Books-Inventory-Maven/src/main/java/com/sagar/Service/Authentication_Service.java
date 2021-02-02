package com.sagar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sagar.dao.Book_DAO;
import com.sagar.model.User;

public class Authentication_Service {
	@Autowired
	private Book_DAO bookDAO;

	public boolean signUp(@ModelAttribute User user,Model model) {

    	String Fname=user.getFname();
    	String Lname=user.getLname();
    	String Email=user.getEmail();
    	String pwd=user.getPwd();
    	
    	boolean status=bookDAO.signUp(Fname,Lname,Email,pwd);
    	return status;
	}

	public int login(String Email, String Pwd, Model model) {
		
    	String email=Email;
    	String pwd=Pwd;
    	
    	int status=bookDAO.signIn(email,pwd); 
    		
		return status;
		
	}
	
}
