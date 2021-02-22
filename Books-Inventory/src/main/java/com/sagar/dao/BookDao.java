package com.sagar.dao;

import java.util.List;

import com.sagar.model.Book;

public interface BookDao {

	List<Book> findAll();

	void save(Book book);

	void update(Book book);

	void delete(int id);

	Book findById(int id);

	// Book findByISBN(int isbn);

	/*
	 * List<Object> getColumnData();
	 * 
	 * List<Object> getColumnNames();
	 */

}