package com.sagar.propertiesLoader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {
	private static PropertiesLoader obj;
	private static Map<String, String> queries = new HashMap<String, String>();

	private PropertiesLoader() {}

	public static String getQuery(String query) {
	  
		  if(obj==null) {
			  obj=new PropertiesLoader();
			  
				System.out.println("=====================setting Queries========================");
				Properties properties = new Properties();
				try (InputStream iss = PropertiesLoader.class.getResourceAsStream("/database_queries.sql")) {
					properties.load(iss);
					for (String name : properties.stringPropertyNames()) {
						queries.put(name, properties.getProperty(name));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}			  
		  }
		  
		  return queries.get(query);	 

}}
