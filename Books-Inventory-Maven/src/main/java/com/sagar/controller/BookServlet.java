package com.sagar.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//import com.mysql.cj.Session;
import com.sagar.dao.BookDAO;
import com.sagar.model.Book;


@Controller
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private BookDAO bookDAO;
    private int excelGenerated=0;

    public void init() {
    	bookDAO = new BookDAO();
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action.toString());
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                case "/generateExcel":  
                	generateExcel(request,response);
                	break;
                case "/list":
                	listBook(request, response);
                    break;
                case "/login":
                	login(request,response);
                	break;
                case "/signup":
                	signup(request,response);
                	break;	
                case "/logout":
                	logout(request,response);
                	break;
                case "/signupPage":
                	signupPage(request,response);
                	break;                	
                default:
                    userLoginPage(request,response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void signupPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher rd=req.getRequestDispatcher("userSignUp.jsp");
    	rd.forward(req, resp);		
	}

	private void logout(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.invalidate();
    	RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
    	rd.forward(req, resp);
    }
    
    private void signup(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	String Fname=req.getParameter("fname");
    	String Lname=req.getParameter("lname");
    	String Email=req.getParameter("email");
    	String pwd=req.getParameter("pwd");   	
    	
    	boolean status=bookDAO.signUp(Fname,Lname,Email,pwd);
    	if(status==false) {
	    	RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
	    	rd.forward(req, resp);
    	}else {
    		RequestDispatcher rd=req.getRequestDispatcher("SignUpError.jsp");
	    	rd.forward(req, resp);
    	}    	
    	
    }
    
    public boolean loginValidator(HttpServletRequest req,HttpServletResponse resp) {
   try {
	Cookie[] arr= req.getCookies();
	System.out.println("====================Length of the cookie is ============="+arr.length);
	
    	 for(int i=0;i<arr.length;i++) {
    		 System.out.println("================"+arr[0].getName()+"========="+arr[0].getValue());	 
    	 }
} catch (Exception e) {
	// TODO: handle exception
}
    	 return false;  	 
    	    
    }   
  
    @PostMapping("/login")
    public void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, SQLException {
    	String email=req.getParameter("email");
    	String pwd=req.getParameter("pwd");
    	
    	int status=bookDAO.signIn(email,pwd); 
    	HttpSession session=req.getSession();
    	session.setAttribute("email", email);
    	    
		
		  if(status==1) {
			  listBook(req, resp);
		  }else { 
			  req.setAttribute("status",status);
			  RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
			  rd.forward(req, resp); 
			  }		 
    }
    
    private void userLoginPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
       	RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
    	rd.forward(req, resp);
    }

    private void generateExcel(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
			excelGenerated= BookDAO.convertToExcel();
			listBook(request,response);			
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Book > listBooks = BookDAO.selectAllBooks();
        listBooks.forEach((it)-> System.out.println(it.getAuthor()));/////////////////////////////printing list here
        request.setAttribute("listBooks", listBooks);
        request.setAttribute("excelGenerated", excelGenerated);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
        dispatcher.forward(request, response);
        excelGenerated=0;
       // BookDAO.convertToExcel();        
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String ISBN = request.getParameter("ISBN");
        String Author = request.getParameter("Author");
        Book newBook = new Book(name, ISBN, Author);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }

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

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        response.sendRedirect("list");
    }
}