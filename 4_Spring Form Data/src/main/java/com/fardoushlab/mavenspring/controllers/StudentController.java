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
import com.fardoushlab.mavenspring.model.Course;
import com.fardoushlab.mavenspring.model.Student;
import com.fardoushlab.mavenspring.services.CountryService;
import com.fardoushlab.mavenspring.services.CourseService;
import com.fardoushlab.mavenspring.services.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;
	@Autowired
	CountryService countryService;
	
	

	@GetMapping("/index")
	public String getStudentPage(Model model) {		
		
		model.addAttribute("student", new Student());
		
		var studentList = new ArrayList<Student>();
		studentList = (ArrayList<Student>) studentService.getStudentList();	
		
		model.addAttribute("student_list",studentList);
		
		return "index";		
	}
	
	@GetMapping("/student/add")
	public String addStudentPage(Model model) {			
		
		model.addAttribute("student",new Student());
		model.addAttribute("countries",countryService.getAll());
		model.addAttribute("courses",courseService.getAllCourses());
		
		return "student/add";
	
		
	}
	
	@PostMapping("/student/add")
	public String addStudent(Model model, @ModelAttribute("student") Student student) {
		studentService.addStudent(student);
		
		model.addAttribute("message","testing");
		return "redirect:/index";
	}
	
	@PostMapping("/student/search")
	public String searchStudent(Model model,  @ModelAttribute("student") Student student) {
		
		model.addAttribute("student", new Student());
		
		var studentList = new ArrayList<Student>();
		studentList = (ArrayList<Student>) studentService.searchStudentByName(student.getName());
		
		model.addAttribute("student_list",studentList);
		
		return "index";
	}
	
	
}
