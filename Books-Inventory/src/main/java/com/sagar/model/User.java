package com.sagar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
 @Table(name = "users")
 public class User {
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name = "FNAME")
	private String fname;
	@Column(name = "LNAME")
	private String lname;

	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String pwd;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public User() {
		
	}
	
	public User(String fname, String lname, String email, String pwd) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.pwd = pwd;
	}

	
	
	

}
