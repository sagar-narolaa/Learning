package com.sagar.Test;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TestConnection {
	 Connection con=null;
	 Properties pr=new Properties();
	
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
	
}
