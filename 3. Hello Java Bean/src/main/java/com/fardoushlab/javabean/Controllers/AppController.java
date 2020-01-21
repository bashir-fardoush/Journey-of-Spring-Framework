package com.fardoushlab.javabean.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fardoushlab.javabean.model.User;
import com.fardoushlab.javabean.service.UserService;

@Controller
public class AppController {
	
	
	@Autowired
	UserService userService;
//
//	@GetMapping("/index")
//	public String helloBean(Model model) {
//	//	model.addAttribute("user",user);
//		userService.showAllUser();
//		return "index";
//	}
//	
//	
	@GetMapping("/show-all")
	public String showAllUser(Model model) {
		model.addAttribute("message","Showing all user");
		userService.showAllUser();		
		return "index";
		
	}
	
	@GetMapping("/add-user")
	public String addUser(@RequestParam("username") String username, Model model) {
		
		userService.createUser(username);
		model.addAttribute("message","User created successfully");
		return "index";		
	}
	@GetMapping("/remove-user")
	public String removeUser(@RequestParam("username") String username, Model model) {
		
		userService.removeUserByName(username);
		model.addAttribute("message","User removed successfully");
		return "index";		
	}
	
}
