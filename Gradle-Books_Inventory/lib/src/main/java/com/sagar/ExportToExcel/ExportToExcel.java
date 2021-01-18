package com.sagar.ExportToExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class ExportToExcel {
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMetaDeta;
	private String sheet_name;
	private String[] tableColoumnNames;
		
	  public ExportToExcel(ResultSet rs,String name,ResultSetMetaData rsMetadata) {
		  this.resultSet=rs;
		  this.sheet_name=name;
		  this.resultSetMetaDeta=rsMetadata;
		  
	  }
	
	public void generateExcel() throws FileNotFoundException, SQLException {
		
		@SuppressWarnings("resource")
		Workbook wb=new HSSFWorkbook() ;
		Sheet sheet=wb.createSheet();
		
		Map<Integer,ArrayList<Object>> data=new TreeMap<>();		
		
		ArrayList<Object> obj=new ArrayList<Object>();
		
		tableColoumnNames=new String[resultSetMetaDeta.getColumnCount()];
		
		ArrayList<ArrayList<Object>> temp=new ArrayList<ArrayList<Object>>();
		
		for (int i = 1; i <=resultSetMetaDeta.getColumnCount(); i++) {
			
			System.out.print( resultSetMetaDeta.getColumnName(i)+" ");//////////////////////////////			
			  obj.add(resultSetMetaDeta.getColumnName(i)) ;			  
			 tableColoumnNames[i-1]=resultSetMetaDeta.getColumnName(i);
		}	
		System.out.println();
		
		//data.put(1,obj);		
		temp.add(obj);
		System.out.println(temp);
		
//		System.out.println("starting =================================");
//		for (String x : tableColoumnNames) {			
//			System.out.println(x);
//		}
//		System.out.println("ending =================================");
		while(resultSet.next()) {		
			ArrayList<Object> tempp=new ArrayList<>();
			for(int i=0;i<tableColoumnNames.length;i++) {
				tempp.add(resultSet.getString(tableColoumnNames[i]));//////////////we can add index here also
			}
			temp.add(tempp);			
		}
		
		System.out.println(temp);//////////////////Printing Entries
		
		
		for (int i = 0; i <temp.size(); i++) {
			data.put(i+1, temp.get(i));
		}
		
		
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
           
            OutputStream out = new FileOutputStream(new File("C:\\Users\\sosagar\\eclipse-workspace\\Gradle-Book_Inventory\\Excel Files\\"+sheet_name+".xls")); 
            wb.write(out); 
            out.close(); 
            System.out.println("books.xlsx written successfully on disk."); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
		
	
		
		
		
	}
	
	
}
