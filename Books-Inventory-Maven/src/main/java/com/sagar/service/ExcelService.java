package com.sagar.service;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;


public interface ExcelService {

	/*
	 * int generateExcel(List<List<Object>> list, List<Object> list2) throws
	 * FileNotFoundException, SQLException;
	 */
	
	List<Object> getColumnData();
	
	List<Object> getColumnNames();
	
	int generateExcel() throws FileNotFoundException, SQLException;
	
	/*
	 * List<Object> getColumnData();
	 * 
	 * List<Object> getColumnNames();
	 */
}
