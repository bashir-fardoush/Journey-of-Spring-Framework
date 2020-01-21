package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import com.fardoushlab.mavenspring.model.Country;

public class CountryService {


	private List<Country> countries = new ArrayList<Country>();
	private static final String[] COUNTRY_NAMES = new String[] {"Bangladesh","India","Pakistan","Japan"};
	
	
	public CountryService() {
		super();
		
		/*final AtomicReference<Integer> atomicId = new AtomicReference<Integer>();
		atomicId.set(0);*/
		
		Stream.of(COUNTRY_NAMES).forEach(country->{
			
			addCountry(country);
				
		});
		
	}
	
	private void addCountry(String countryName) {
		
		var countryObj = new Country();
		
		countryObj.setId(countries.size()+1);
		countryObj.setCountryName(countryName);
		countryObj.setCountryCode(countryName.substring(0, 3));
		countries.add(countryObj);
		
	}
	
	public void addCountry(Country country) {
		checkCountryInList(country);
		country.setId(countries.size()+1 );
		countries.add(country);
	}
	
	private void checkCountryInList(Country c) {
		
		if (countries
				.stream()
				.filter(country-> country.getCountryCode().equals(c.getCountryCode()))
				.findAny().isPresent()) {
			
			throw new RuntimeException("Country exists in list");
		}
		
	
	}
	public Country getCountryByCode(String countryCode) {
		
		return countries.stream()
				.filter(country-> country.getCountryCode().equals(countryCode))
				.findAny()
				.orElseThrow(()->new RuntimeException("Country not found with this code"));
				
	}
	
	public List<Country> getAll() {
		
		return countries;
	}
	
	

}
