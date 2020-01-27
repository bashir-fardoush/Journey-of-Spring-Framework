package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fardoushlab.mavenspring.exception.ResourceAlreadyExistsException;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;
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
		countryObj.setCountryCode(countryName.substring(0, 3).trim().toUpperCase());
		countries.add(countryObj);
		
	}
	
	public void addCountry(Country country) {
		checkCountryInList(country);
		country.setCountryCode(country.getCountryCode().trim().toUpperCase());
		country.setId(countries.size()+1 );
		countries.add(country);
	}
	
	public void saveEditedCountry(Country c) {
		
		countries
		.stream()
		.filter(country-> country.getCountryCode().equals(c.getCountryCode()))
		.findAny().get().setCountryName(c.getCountryName());
		

	}
	
	private void checkCountryInList(Country c) {
		
		if (countries
				.stream()
				.filter(country-> country.getCountryCode().equals(c.getCountryCode().trim().toUpperCase()))
				.findAny().isPresent()) {
			
			throw new ResourceAlreadyExistsException("Country exists in list");
		}
		
	
	}
	public Country getCountryByCode(String countryCode) {
		
		return countries.stream()
				.filter(country-> country.getCountryCode().equals(countryCode.trim().toUpperCase()))
				.findAny()
				.orElseThrow(()-> new ResourceNotFoundException("Country not found with this code"));
		
				
	}
	public void deleteCountryByCode(String countryCode) {
		/*countries.forEach(country-> {
			
			if(country.getCountryCode().equals(countryCode)) {
				countries.remove(country);
				
			}
		});*/
		
		for (int i = 0 ; i<countries.size(); i++) {
			if(countries.get(i).getCountryCode().equals(countryCode)) {
				countries.remove(i);
				break;				
			}
		}
		
		
	}
	
	public List<Country> getAll() {
		
		return countries;
	}
	
	

}
