package com.fardoushlab.springpractice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/index")
	public String helloWorld() {
		return "index";
	}
}
