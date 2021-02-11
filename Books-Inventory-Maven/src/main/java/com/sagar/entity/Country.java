
  package com.sagar.entity;
  
  import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; import
  javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
  
  @Entity
  @Table(name="countryy")
  public class Country {
  
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int countryId;
  
  @Column(name="name")
  private String name;
  
  
	/*
	 * @OneToOne(mappedBy = "country") private Address address;
	 */
  
	/*
	 * @ManyToOne private State state;
	 */

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
 