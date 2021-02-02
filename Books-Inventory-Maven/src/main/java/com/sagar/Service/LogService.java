package com.sagar.Service;

import org.apache.log4j.Logger;  
  

  
public class LogService{  
  
      Logger log = Logger.getLogger(LogService.class.getName());  
     
      public Logger getLogger(){
    	  return this.log;
      }
   
   
	/*
	 * public static void main(String[] args)throws IOException, SQLException{
	 * log.debug("Hello this is a debug message");
	 * log.info("Hello this is an info message"); }
	 */ 
}  