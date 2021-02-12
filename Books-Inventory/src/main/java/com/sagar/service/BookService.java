package com.sagar.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sagar.model.Book;

public interface BookService {

	public List<Book> getAll();
	
	void save(Book book);
	
	void update(Book book);
	
	void delete(int id);
	
	Book getById(int id);
	
	//Book getByISBN(int isbn);
	
	int exportToExcel() throws FileNotFoundException, SQLException;

}
