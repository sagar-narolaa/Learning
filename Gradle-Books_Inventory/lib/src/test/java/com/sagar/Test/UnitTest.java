package com.sagar.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
	Testing ts=new Testing();
	
	@Test
	void testConnection() {
		try {
			
			InputStream is= UnitTest.class.getResourceAsStream("/database.properties");
			Properties pr=new Properties();
			pr.load(is);
			Class.forName(pr.getProperty("db_driver"));
			Connection con=DriverManager.getConnection(pr.getProperty("db_url"),pr.getProperty("db_username"), pr.getProperty("db_pwd"));
			System.out.println("Connection Established");			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("Exception while getting a connection");
			e.printStackTrace();
		}
	}
	

    @Test
    void testGet() {
    	System.out.println("=============Calling from testGet()==============");
        assertEquals("Hello Testing", ts.get());
    }
    
    @Test
    void testAdd() {
    	System.out.println("=============Calling from testAdd()===========");
    	assertEquals(10,ts.addTwo(5,5));
    }  
    
}