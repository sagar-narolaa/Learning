package com.sagar.Service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.sagar.dao.Book_DAO;

public class Excel_Service {

	
	@Autowired
	private Book_DAO bookDAO;	
	

	public int generateExcel() throws FileNotFoundException {
		return bookDAO.convertToExcel();	
	}
	

}
