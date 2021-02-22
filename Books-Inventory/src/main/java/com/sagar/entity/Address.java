
package com.sagar.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {

	@Id
	@Column(name = "addressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addrId;

	@Column(name = "address1")
	private String addrLine1;

	@Column(name = "address2")
	private String addrLine2;

	@Column(name = "address3")
	private String addrLine3;

	@Column(name = "pincode")
	private String pincode; 

	@Column(name = "addressType")
	private String addrType;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "userId", nullable = false) private UserEntity user;
	 */

	@OneToOne 
	@JoinColumn(name = "cityId", nullable = false)
	private City city;

	@OneToOne 
	private State state;

	@OneToOne 
	private Country country;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getAddrId() {
		return addrId;
	}

	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getAddrLine3() {
		return addrLine3;
	}

	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {

		String regex = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(pincode);

		if (m.matches()) {
			this.pincode = pincode;
		} else {
			System.out.println("Enter Correct Pincode");
		}
		 
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	/*
	 * public UserEntity getUser() { return user; }
	 * 
	 * public void setUser(UserEntity user) { this.user = user; }
	 */

}
