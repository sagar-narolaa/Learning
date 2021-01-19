package com.sagar.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class Testing {
	
	public String get() {
		return "Hello Testing";
	}
	
	public int addTwo(int a,int b) {
		return a+b;
	}
	
	public static void main(String[] args) throws IOException {
		String path="../Excel Files/books.xls";	
		File file=new File("../Excel Files");
		
		 boolean x= file.mkdir();
		
		 if(x==true) {
			 System.out.println("creting directory succesfull");
		 }else {
			 System.out.println("creating directory failed");
		 }
		 
		 file=new File(path);
		System.out.println(file.getAbsolutePath());
		OutputStream os=new FileOutputStream(file);
		os.close();
		
	   
	}
	
	
	
	
}
