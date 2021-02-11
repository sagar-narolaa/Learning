package com.sagar.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sagar.dao.BookDao;
import com.sagar.model.Book;

@Service("ExcelJPA")
public class ExcelServiceJPAimpl implements ExcelService{
	@Autowired
	@Qualifier("booksJPA")
	BookDao bookDao;
	
	
	@Override
	public List<Object> getColumnData() {
		List<Book> books=bookDao.findAll();
		List<Object> obj=new ArrayList<Object>();
		books.forEach((book)-> obj.add(book));
		return obj;
		}		
	
	@Override
	public List<Object> getColumnNames() {	
		List<Object> obj=new ArrayList<Object>();		
		 Book.getColumnNames().forEach((book)->obj.add(book));
		 return obj;
	}
	
	@Override
	public int generateExcel() throws FileNotFoundException, SQLException {
		List<Object> columnData=getColumnData();
		List<Object> columnNames=getColumnNames();
		@SuppressWarnings("resource")
		Workbook wb=new HSSFWorkbook() ;
		Sheet sheet=wb.createSheet();
		
		Map<Integer,ArrayList<Object>> data=new TreeMap<>();		
		
		//ArrayList<Object> obj=new ArrayList<Object>();
		
		//tableColoumnNames=new String[resultSetMetaDeta.getColumnCount()];	
	
		//tableColoumnNames=new String[list.size()];
		//ArrayList<ArrayList<Object>> temp=new ArrayList<ArrayList<Object>>();
		
		/*
		 * for (int i = 1; i <=resultSetMetaDeta.getColumnCount(); i++) {
		 * 
		 * System.out.print(
		 * resultSetMetaDeta.getColumnName(i)+" ");//////////////////////////////
		 * obj.add(resultSetMetaDeta.getColumnName(i)) ;
		 * tableColoumnNames[i-1]=resultSetMetaDeta.getColumnName(i); }
		 */
		//System.out.println();
		
		//data.put(1,obj);		
		//temp.add((ArrayList<Object>) columnNames);
		//System.out.println(temp);
		
//		System.out.println("starting =================================");
//		for (String x : tableColoumnNames) {			
//			System.out.println(x);
//		}
//		System.out.println("ending =================================");
		
		/*
		 * while(resultSet.next()) { ArrayList<Object> tempp=new ArrayList<>(); for(int
		 * i=0;i<tableColoumnNames.length;i++) {
		 * tempp.add(resultSet.getString(tableColoumnNames[i]));//////////////we can
		 * addindex here also } temp.add(tempp); }
		 */
		 
		
		//System.out.println(temp);//////////////////Printing Entries
		
		data.put(0,(ArrayList<Object>) columnNames );		

		for (int i = 0; i <columnData.size(); i++) {
			ArrayList<Object> obj=new ArrayList<Object>();
		   Book book=(Book)columnData.get(i);
		   obj.add(book.getId());
		   obj.add(book.getName());
		   obj.add(book.getISBN());
		   obj.add(book.getAuthor());			
			data.put(i+1, obj);
		}	
		
		/*
		 * for (int i = 0; i <columnData.size(); i++) { data.put(i+1,
		 * (ArrayList<Object>) columnData.get(i)); }
		 */	
		
		Set<Integer> keyset = data.keySet();
		keyset.forEach((it)->System.out.println(it));////////////////////
        int rownum = 0; 
        for (Integer key : keyset) {           
            Row row = sheet.createRow(rownum++); 
            ArrayList<Object> objArr = data.get(key); 
            int cellnum = 0; 
            for (Object objj : objArr) { 
                // this line creates a cell in the next column of that row 
                Cell cell = row.createCell(cellnum++); 
                if (objj instanceof String) 
                    cell.setCellValue((String)objj); 
                else if (objj instanceof Integer) 
                    cell.setCellValue((Integer)objj); 
            } 
        } 
        try {     		
        	InputStream iss = BookDao.class.getResourceAsStream("/file_paths.properties");
        	Properties pr=new Properties();
			pr.load(iss);
    		//String path=pr.getProperty("excel_path")+sheet_name+".xls";	
			
    		File file=new File("C:\\Users\\sosagar\\eclipse-workspace\\Books-Inventory-Maven\\"+"books"+".xls");
    	//	System.out.println(file.getAbsolutePath());
    		OutputStream out=new FileOutputStream(file);
        	
        	
       //     OutputStream out = new FileOutputStream(new File("C:\\Users\\sosagar\\eclipse-workspace\\Gradle-Books_Inventory\\Excel Files\\"+sheet_name+".xls")); 
            wb.write(out); 
            out.close(); 
            System.out.println("books.xls written successfully on disk."); 
            return 1;
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
            return -1;
        }        
            
	}	

}
