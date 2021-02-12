
package com.sagar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State {

	@Id
	@Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stateId;

	@Column(name = "name")
	private String stateName;
	
	
	@OneToMany(mappedBy = "state")
	private List<City> cities;
	 
	
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "country_id")
		private Country country;

		public List<City> getCities() {
			return cities;
		}

		public void setCities(List<City> cities) {
			this.cities = cities;
		}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStateName() {
		return stateName;
	}

	public int getId() {
		return stateId;
	}

	public void setId(int id) {
		this.stateId = id;
	}

	public String getstateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName =stateName;
	}

	
	
	/*
	 * public City getCity() { return city; }
	 * 
	 * public void setCity(City city) { this.city = city; }
	 */
	 
	 
}
