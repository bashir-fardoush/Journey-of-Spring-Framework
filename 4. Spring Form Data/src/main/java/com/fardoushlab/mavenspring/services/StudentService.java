package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fardoushlab.mavenspring.model.Country;

public class StudentService {
	

	private List<Country> countries = new ArrayList<Country>();
	private static final String[] COUNTRY_NAMES = new String[] {"Bangladesh","India","Pakistan","Japan"};
	
	
	public StudentService() {
		super();
		
		/*final AtomicReference<Integer> atomicId = new AtomicReference<Integer>();
		atomicId.set(0);*/
		
		Stream.of(COUNTRY_NAMES).forEach(country->{
			
			addCountry(country);
				
		});
		
	}
	
	public void addCountry(String countryName) {
		
		var countryObj = new Country();
		
		countryObj.setId(countries.size()+1);
		countryObj.setCountryName(countryName);
		countryObj.setCountryCode(countryName.substring(0, 3));
		countries.add(countryObj);
		
	}
	
	public Country getCountryByCode(String countryCode) {
		
		return countries.stream().filter(country-> country.getCountryCode().equals(countryCode)).findFirst().get();
		
		
	}
	
	
}
