package com.sagar.propertiesLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

public static Properties loadPropertiesFile(String path){
	  
	 Properties properties = new Properties();
    InputStream iStream = null;
      try {
        // Loading properties file from the path (relative path given here)
    	  
        iStream = new FileInputStream(path);   
        properties.load(iStream);
        System.out.println(properties.getProperty("db_url")); 
        
      } catch (IOException e) {
    	  System.out.println("exception in Properties class");
       e.printStackTrace();
      }finally {
        try {
          if(iStream != null){
            iStream.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        
      }return properties;
    }
  
  /**
  * Method to read the properties from a
  * loaded property file
  */

}