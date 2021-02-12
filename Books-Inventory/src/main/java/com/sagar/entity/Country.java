
  package com.sagar.entity;
  
  import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; import
  javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
  
  @Entity
  @Table(name="countries")
  public class Country {
  
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int countryId;
  
  @Column(name="name")
  private String name;
  
	@OneToMany(mappedBy = "country")
	private List<State> state;

	public List<State> getState() {
		return state;
	}

	public void setState(List<State> state) {
		this.state = state;
	}
	 

public int getCountryId() {
	return countryId;
}

public void setCountryId(int countryId) {
	this.countryId = countryId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

/*s*/
  
  
  
  }
 