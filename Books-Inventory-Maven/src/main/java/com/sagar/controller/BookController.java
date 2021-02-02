package com.sagar.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
//import org.apache.log4j.Logger;  

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagar.Service.BookService;
import com.sagar.Service.DatabaseService;
import com.sagar.Service.Excel_Service;
import com.sagar.Service.LogService;
import com.sagar.dao.BookDao;
import com.sagar.model.Book;


@Controller
public class BookController {
	
	@Autowired
	private BookService crud_Service;
	
	  @Autowired private Excel_Service excel_Service;
	  
	  @Autowired private DatabaseService database_Service;
	  
	  @Autowired private LogService log_Service;
	  
	  @Autowired private BookDao bookDAO;
	 
    
    private int excelGenerated=0;
    
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
       // System.out.println(existingBook.toString());//////////////////////////////
       log_Service.getLogger().info(existingBook);;
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
    private String deleteBook(@PathVariable("id") int id,Model model)  throws SQLException, IOException, ServletException {
    	crud_Service.deleteBook(id);      
    	List < Book > listBooks = database_Service.getBooks();
		  model.addAttribute("listBooks", listBooks);
		  int excel_status=excelGenerated;
		  model.addAttribute("excelGenerated",excel_status); excelGenerated=0;
		  return "book-list";

    	//return "redirect:/list";
    }
    
	
	 
}