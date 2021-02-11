
package com.sagar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "Fname")
	private String fname;

	@Column(name = "Lname")
	private String lname;

	@Column(name = "Email")
	private String email;

	@Column(name = "PASSWORD")
	private String pwd;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Address> addrList = new ArrayList<Address>();

	public int getId() {
		return userId;
	}

	public void setId(int userId) {
		this.userId = userId;
	}

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

	public List<Address> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<Address> addrList) {
		this.addrList = addrList;
	}

}
