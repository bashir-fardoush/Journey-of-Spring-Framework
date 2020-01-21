package com.fardoushlab.mavenspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		
		return "redirect:/country/show-all";
		
	}
	
	@GetMapping("/country/show-all")
	public String showAllCountry(Model model) {
		
		model.addAttribute("country_list",countryService.getAll());
		model.addAttribute("message","Showing all countries");		
		
		return "country/show-all";
		
	}

}
