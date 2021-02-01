package com.sagar.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.mysql.cj.Session;
import com.sagar.dao.Book_DAO;
import com.sagar.model.Book;


@Controller
public class Book_Controller {
    
    @Autowired
    private Book_DAO bookDAO;
    private int excelGenerated=0;
   
    
    @GetMapping("/signupPage")
    private String signupPage() throws  IOException {
    	return "userSignUp";
		/*
		 * RequestDispatcher rd=req.getRequestDispatcher("userSignUp.jsp");
		 * rd.forward(req, resp);
		 */	
	}
    
    @GetMapping("/logout")
	private String logout(HttpSession session) throws IOException {		
		session.invalidate();		
		return "redirect:userLogin.jsp"; 
		/*
		 * RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
		 * rd.forward(req, resp);
		 */
    }
    
    @PostMapping("/signup")
    private void signup(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
    	    	
    	String Fname=req.getParameter("fname");
    	String Lname=req.getParameter("lname");
    	String Email=req.getParameter("email");
    	String pwd=req.getParameter("pwd");   	
    	System.out.println(Fname+Lname);
    	
    	boolean status=bookDAO.signUp(Fname,Lname,Email,pwd);
    	req.setAttribute("signup_status",status);
    	
		/* if(status) { */ 		
	    	RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
	    	rd.forward(req, resp);
/*    	}else {    		
    		RequestDispatcher rd=req.getRequestDispatcher("SignUpError.jsp");
	    	rd.forward(req, resp);
    	} */   	
    	
    }
    
	/*
	 * public boolean loginValidator(HttpServletRequest req,HttpServletResponse
	 * resp) { try { Cookie[] arr= req.getCookies(); System.out.
	 * println("====================Length of the cookie is ============="+arr.
	 * length);
	 * 
	 * for(int i=0;i<arr.length;i++) {
	 * System.out.println("================"+arr[0].getName()+"========="+arr[0].
	 * getValue()); } } catch (Exception e) { // TODO: handle exception } return
	 * false;
	 * 
	 * }
	 */  
  
    @PostMapping("/login")
    public String login( HttpServletRequest req,HttpServletResponse resp,Model model) throws IOException, SQLException, ServletException {
    	String email=req.getParameter("email");
    	String pwd=req.getParameter("pwd");
    	
    	int status=bookDAO.signIn(email,pwd); 
    	
    	HttpSession session=req.getSession();
    	session.setAttribute("email", email);
    	    
		
		  if(status==1) {
			  
			  return "redirect:/list";
			 // listBook(req, resp);
		  }else { 
			  model.addAttribute("status",status);
			  return "userLogin";
				/*
				 * RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
				 * rd.forward(req, resp);
				 */ 
			  }		 		
    }
    
    @GetMapping("/")
    private String userLoginPage() throws IOException {
    	return "userLogin";
    }
    
    @GetMapping("/generateExcel")
    private String generateExcel(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException, IOException, ServletException {
			excelGenerated= bookDAO.convertToExcel();			 	 
			 return "redirect:/list";
			//listBook(request,response);			
	}
    
    @RequestMapping("/list-book")
    public String listnbook() {
    	return "book-list";
    }
    
    
    @RequestMapping("/list")
	private String listBook(HttpServletRequest req,HttpServletResponse resp,Model model)
    throws SQLException, IOException, ServletException {
        List < Book > listBooks = bookDAO.selectAllBooks();
        listBooks.forEach((it)-> System.out.println(it.getAuthor()));/////////////////////////////printing list here
       
        
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("excelGenerated", excelGenerated);
       
        return "book-list";
        
		/*
		 * RequestDispatcher rd=req.getRequestDispatcher("books-list.jsp");
		 * rd.forward(req, resp); //return "redirect:book-list.jsp"; excelGenerated=0;
		 */        
    }
    
    @GetMapping("/new")
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }
    
    @GetMapping("/edit")
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }
    
    @PostMapping("/insert")
    private void insertBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String ISBN = request.getParameter("ISBN");
        String Author = request.getParameter("Author");
        Book newBook = new Book(name, ISBN, Author);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }
    
    @PostMapping("/update")
    private void updateBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String ISBN = request.getParameter("ISBN");
        String Author = request.getParameter("Author");

        Book book = new Book(id, name, ISBN, Author);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }
    
    @GetMapping("/delete")
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        response.sendRedirect("list");
    }
}