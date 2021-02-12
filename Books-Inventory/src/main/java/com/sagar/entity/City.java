
package com.sagar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sagar.main.EntityDAO;

@Entity
@Table(name = "cities")
public class City {
	@Id
	@Column(name = "cityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cityId;	

	@Column(name = "name")
	private String cityName;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private State state;
	 


	public void setState(State state) {		
		this.state = state;
	}
	
	public State getState() {		
		return state;
	}
	
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
