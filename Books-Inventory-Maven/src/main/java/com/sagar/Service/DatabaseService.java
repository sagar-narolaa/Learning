package com.sagar.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sagar.dao.Book_DAO;
import com.sagar.model.Book;

public class DatabaseService {
	
	@Autowired
	  private Book_DAO bookDAO;

	public List<Book> getBooks() {
		return bookDAO.selectAllBooks();		
	}

}
