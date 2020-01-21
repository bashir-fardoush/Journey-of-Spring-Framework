package com.fardoushlab.mavenspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fardoushlab.mavenspring.model.Course;
import com.fardoushlab.mavenspring.services.CourseService;
@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/course/add")
	public String getAddCoursePage(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("message","Add a new Course");	
		
		return "course/add";
		
	}
	
	@PostMapping("/course/add")
	public String addCourse(Model model, @ModelAttribute(name = "course") Course course) {
		courseService.addCourse(course);
		model.addAttribute("message","Course added successfully");
		
		return "redirect:/course/show-all";
		
	}
	
	@GetMapping("/course/show-all")
	public String showAllCourse(Model model) {
		model.addAttribute("course_list",courseService.getAllCourses());
		model.addAttribute("message","Showing all course...");
		
		return "/course/show-all";
	}

}
