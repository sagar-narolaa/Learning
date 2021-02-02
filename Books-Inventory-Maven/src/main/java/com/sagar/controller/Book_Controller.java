package com.sagar.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagar.Service.Authentication_Service;
import com.sagar.Service.CRUD_Service;
import com.sagar.Service.DatabaseService;
import com.sagar.Service.Excel_Service;
//import com.mysql.cj.Session;
import com.sagar.dao.Book_DAO;
import com.sagar.model.Book;
import com.sagar.model.User;


@Controller
public class Book_Controller {
	
	@Autowired
	private CRUD_Service crud_Service;
	@Autowired
	private Excel_Service excel_Service;
	@Autowired
	private DatabaseService database_Service;
	@Autowired
	private Authentication_Service authentication_Service;
    
    @Autowired
    private Book_DAO bookDAO;
    
    private int excelGenerated=0;
   
    
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
    
    @GetMapping("/")
    private String userLoginPage() throws IOException {
    	return "userLogin";
    }
    
    @GetMapping("/generateExcel")
    private String generateExcel(Model model) throws SQLException, IOException, ServletException {
    		excelGenerated=excel_Service.generateExcel();
			 return "redirect:/list";		
	}
    
    
    @RequestMapping("/list")
	private String listBook(Model model) throws SQLException, IOException, ServletException {
        List < Book > listBooks =  database_Service.getBooks();       
        model.addAttribute("listBooks", listBooks);
        int excel_status=excelGenerated;
        model.addAttribute("excelGenerated",excel_status);       
        excelGenerated=0;
        return "book-list";             
    }
    
    @GetMapping("/add")
    private String showNewForm() throws ServletException, IOException {
    	return "book-form";
    }
    
  
    
    @GetMapping("/edit/{id}")
    private String showEditForm(@PathVariable("id") int idd,Model model) throws SQLException, ServletException, IOException {    	
        Book existingBook =crud_Service.getExistingBook(idd); 
        System.out.println(existingBook.toString());//////////////////////////////
        model.addAttribute("book", existingBook);
        return "book-form";
    }
    
   
    @PostMapping("/add")    
    private String insertBook(@RequestParam("name") String namee, @RequestParam("ISBN") String isbn,@RequestParam("Author") String author) throws SQLException, IOException {
       Book newBook=crud_Service.addNewBook(namee,isbn,author);    	
        bookDAO.insertBook(newBook);
        return "redirect:/list";        
    }
    
    @PostMapping("/edit/update")
    private String updateBook(@ModelAttribute Book book) throws SQLException, IOException {
    	crud_Service.updateBook(book);       
        return "redirect:/list";    
    }
    
    @GetMapping("/delete/{id}")
    private String deleteBook(@PathVariable("id") int id)  throws SQLException, IOException {
    	crud_Service.deleteBook(id);        
        return "redirect:/list";
    }
}