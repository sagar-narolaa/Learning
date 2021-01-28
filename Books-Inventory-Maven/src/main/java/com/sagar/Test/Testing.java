package com.sagar.Test;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

interface EmailMethods {
	void sendMail();
}

class CustomEmail implements EmailMethods{
	@Override
	public void sendMail() {
		System.out.println("From Custom Email");
	}	
}

class Email implements EmailMethods{
	@Override
	public void sendMail() {
		System.out.println("Sending Email");		
	}	
}

class EmailFactory{
	
	private Email methods;

	public EmailFactory(Email methods) {
		this.methods=methods;
	}
}

@WebServlet("/test")
public class Testing {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out= response.getWriter();
		//out.append();

	}
	
	public String get() {
		return "Hello Testing";
	}
	
	public int addTwo(int a,int b) {
		return a+b;
	}	
	
}
