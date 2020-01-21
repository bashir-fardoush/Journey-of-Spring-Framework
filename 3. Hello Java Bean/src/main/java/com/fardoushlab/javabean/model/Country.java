package com.fardoushlab.javabean.model;

public class Country {

	private long id;
	private String countryName;
	private String countryCode;
	
	public Country() {
		
	}
	
	public Country(long id, String countryName, String countryCode) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.countryCode = countryCode;
	}
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getCountryName() {
//		return countryName;
//	}
//	public void setCountryName(String countryName) {
//		this.countryName = countryName;
//	}
//	public String getCountryCode() {
//		return countryCode;
//	}
//	public void setCountryCode(String countryCode) {
//		this.countryCode = countryCode;
//	}

	
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode + "]";
	}
	
	
}
