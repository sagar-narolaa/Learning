package com.sagar.dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.sagar.model.Book;


public interface BookDao {

	List<Book> findAll();

	void save(Book book);

	void update(Book book);

	void delete(int id);

	Book findById(int id);

	//Book findByISBN(int isbn);
	
	/*
	 * List<Object> getColumnData();
	 * 
	 * List<Object> getColumnNames();
	 */

}