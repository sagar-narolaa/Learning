package com.sagar.Test;

import org.junit.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
	Testing ts=new Testing();
	static Connection con=null;
	static Properties pr=new Properties();
	
	@Test
	void testConnection() {
		try {			
			InputStream is= UnitTest.class.getResourceAsStream("/database.properties");			
			pr.load(is);
			Class.forName(pr.getProperty("db_driver"));
			con=DriverManager.getConnection(pr.getProperty("db_url"),pr.getProperty("db_username"), pr.getProperty("db_pwd"));
			System.out.println("Connection Established to the "+pr.getProperty("db_url")+"/"+pr.getProperty("db_username")+"/"+pr.getProperty("db_pwd"));			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("Exception while getting a connection");
			e.printStackTrace();
		}
	}   

    @Test
    void testGet() {
    	assertEquals("jdbc:mysql://localhost:3306/books_inventory", pr.getProperty("db_url"));
    	System.out.println("=============Calling from testGet()==============");
        assertEquals("Hello Testing", ts.get());
    }
    
    @Test
    void testAdd() {    	
    	System.out.println("=============Calling from testAdd()===========");
    	assertEquals(10,ts.addTwo(5,5));
    }   

    
}