package com.sagar.Service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.sagar.dao.BookDao;

public class Excel_Service{

	
	@Autowired
	private BookDao bookDAO;	
	

	public int generateExcel() throws FileNotFoundException {
		return bookDAO.convertToExcel();	
	}
	

}
