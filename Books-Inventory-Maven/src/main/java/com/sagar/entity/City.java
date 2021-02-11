
package com.sagar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cityy")
public class City {

	@Id
	@Column(name = "cityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cityId;	

	@Column(name = "name")
	private String cityName;	
	 
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToOne (cascade = CascadeType.ALL)
	private State state;
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	


}
