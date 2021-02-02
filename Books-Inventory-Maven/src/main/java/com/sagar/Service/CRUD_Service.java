package com.sagar.Service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sagar.dao.Book_DAO;
import com.sagar.model.Book;
import com.sagar.model.User;

@Service
public class CRUD_Service {
	@Autowired
	private Book_DAO bookDAO;

	public Book getExistingBook(int id) {
		return bookDAO.selectBook(id);
	}

	public Book addNewBook(String name, String isbn, String author) {		
		String Name = name;
        String ISBN = isbn;
        String Author = author;
        Book newBook = new Book(Name, ISBN, Author);
        return newBook;
	}

	public void updateBook(Book book) throws SQLException {    	
        int id = book.getId();        
        String name = book.getName();
        String ISBN = book.getISBN();
        String Author = book.getAuthor();
        Book updatedBook = new Book(id,name, ISBN, Author);
        bookDAO.updateBook(updatedBook);
        
	}

	public void deleteBook(int idd) throws SQLException {
		int id = idd;
        bookDAO.deleteBook(id);		
	}


	
	

}
