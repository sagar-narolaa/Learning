package com.sagar.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
	Testing ts=new Testing();

    @Test
    void testGet() {
    	System.out.println("=============Calling from testGet()==============");
        assertEquals("Hello Testing", ts.get());
    }
    
    @Test
    void testAdd() {
    	System.out.println("=============Calling from testAdd()===========");
    	assertEquals(11,ts.addTwo(5,5));
    }  
    
}