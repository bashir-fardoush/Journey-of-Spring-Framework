package com.fardoushlab.mavenspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

	@GetMapping("/index")
	public String hello() {
		
		return "index";		
	}
	
	@GetMapping("/userPage")
	public String showUser(@RequestParam("userName") String userName, Model model){
		
		model.addAttribute("userName", userName);		
		return "userPage";
	} 
}
