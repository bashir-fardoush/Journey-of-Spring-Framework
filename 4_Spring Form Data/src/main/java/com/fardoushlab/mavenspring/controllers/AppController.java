package com.fardoushlab.mavenspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

	  @GetMapping("/login")
	  public String loginPage(Model model, @RequestParam(name="error", required = false) Boolean error) {
	  
		  model.addAttribute("error",error);
	  return "auth/login"; 
	  }
	 
}
