package com.fardoushlab.mavenspring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="country_code", unique=true, nullable=false, updatable=false)
	private String countryCode;
	
	@Column(name="country_name", nullable=false)
	private String countryName;
	
	
	public Country() {
		
	}


	public Country(int id, String countryCode, String countryName) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	@Override
	public String toString() {
		return "Country [id=" + id + ", countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}
	
	
	
	

}
