package com.sagar.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sagar.dao.BookDAO;
import com.sagar.model.Book;

@WebServlet("/")
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
                default:
                    listBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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