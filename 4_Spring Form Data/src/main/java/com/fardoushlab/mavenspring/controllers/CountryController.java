package com.fardoushlab.mavenspring.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fardoushlab.mavenspring.model.Country;
import com.fardoushlab.mavenspring.services.CountryService;

@Controller
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/country/add")
	public String getAddCountryPage(Model model) {
		
		model.addAttribute("country", new Country());
		model.addAttribute("message", "Please add a country");
		
		return "country/add";
	}
	
	
	@PostMapping("/country/add")
	public String addCountry(Model model, @ModelAttribute(name = "country") Country country) {
		
		countryService.addCountry(country);
		model.addAttribute("message", "Country added successfully");
		
	//	return "redirect:/country/show-all";/country/countries
		return "redirect:/country/countries";
		
	}
	
	/*@GetMapping("/country/show-all")
	public String showAllCountry(Model model) {
		
		model.addAttribute("country_list",countryService.getAll());
		model.addAttribute("message","Showing all countries");		
		
		return "country/show-all";
		
	}*/
	
	@GetMapping("/country/countries")
	public String showCountries(Model model) {
		
		model.addAttribute("country_list",countryService.getAll());
		model.addAttribute("country", new Country());	
		
		return "country/countries";
		
	}
	
	@PostMapping("/country/search/")
	public String searchCountry(Model model, @ModelAttribute(name = "country") Country country) {
		var countryList = new ArrayList<Country>();
		countryList.add(countryService.getCountryByCode(country.getCountryCode()));
		model.addAttribute("country_list",countryList);
		
		return "country/countries";
		
	}
	
	@GetMapping("/country/edit")
	public String editCountry(Model model, @RequestParam("countryCode") String code) {
		
		model.addAttribute("country", countryService.getCountryByCode(code));
		model.addAttribute("message", code);		 
		
		return "country/edit";
		
	}
	
	@PostMapping("/country/edit")
	public String saveEditedCountry(Model model, @ModelAttribute(name = "country") Country country) {
		
		countryService.saveEditedCountry(country);
		model.addAttribute("message", "Country added successfully");
		
		return "redirect:/country/countries";
		
	}
	
	@GetMapping("/country/delete")
	public String deleteCountryByCode(Model model, @RequestParam("countryCode") String code) {
		
		countryService.deleteCountryByCode(code);
		model.addAttribute("message","Country deleted successfully");
		return "redirect:/country/countries";
	}

	
}
