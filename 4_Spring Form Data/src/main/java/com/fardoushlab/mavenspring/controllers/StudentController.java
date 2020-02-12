package com.fardoushlab.mavenspring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fardoushlab.mavenspring.dtos.StudentBasicDto;
import com.fardoushlab.mavenspring.dtos.StudentDto;
import com.fardoushlab.mavenspring.model.Country;
import com.fardoushlab.mavenspring.model.Course;
import com.fardoushlab.mavenspring.requestModels.Student;
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
	
	
	@GetMapping("/")
	public String getHomePage(Model model) {	
		
		return "redirect:index";		
		
	}
	
	

	@GetMapping("/index")
	public String getStudentPage(Model model) {		
		
		model.addAttribute("student", new Student());
		
		var studentBasicDtoList = new ArrayList<StudentBasicDto>();
		studentBasicDtoList = (ArrayList<StudentBasicDto>) studentService.getStudentList();	
		
		var studentList = new ArrayList<Student>();				
		studentBasicDtoList.forEach(dto->{
			var student = new Student();
			BeanUtils.copyProperties(dto, student);
			studentList.add(student);
		});
		
		model.addAttribute("student_list",studentList);		
		return "index";		
	}
	
	@GetMapping("/student/add")
	public String addStudentPage(Model model) {			

		//model.addAttribute("student",new Student());
		model.addAttribute("student", new Student());
		model.addAttribute("countries",countryService.getAll());
		model.addAttribute("courses",courseService.getAllCourses());
		
		return "student/add";
	
		
	}
	
	@PostMapping("/student/add")
	public String addStudent(Model model, @ModelAttribute("student") Student student) {
	
		var studentDto = new StudentDto();
		BeanUtils.copyProperties(student, studentDto);
		
		studentService.addStudent(studentDto);
		
		model.addAttribute("message","testing");
		return "redirect:/index";
	}
	
	@PostMapping("/student/search")
	public String searchStudent(Model model,  @ModelAttribute("student") Student student) {
		
		model.addAttribute("student", new Student());
		
		var studentBasicDtoList = new ArrayList<StudentBasicDto>();
		studentBasicDtoList = (ArrayList<StudentBasicDto>) studentService.searchStudentByName(student.getName());
		
		var studentList =new ArrayList<Student>(); 
		studentBasicDtoList.forEach(dto->{
			
			var tempstudent = new Student();
			BeanUtils.copyProperties(dto, tempstudent);			
			studentList.add(tempstudent);		
			
		});
		
		model.addAttribute("student_list",studentList);
		
		return "index";
	}
	
	@GetMapping("/student/edit")
	public String editStudent(Model model, @RequestParam("id") int studentId){
		
		var studentDto = studentService.getStudentById(studentId);
		var student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		
		model.addAttribute("student",student);
		model.addAttribute("countries",countryService.getAll());
		model.addAttribute("courses",courseService.getAllCourses());		
		
		return "student/edit";
	}
	
	@PostMapping("/student/edit")
	public String saveEditedStudent(Model model, @ModelAttribute("student") Student student) {
		
		var studentDto = new StudentDto();
		BeanUtils.copyProperties(student, studentDto);
		studentService.saveEditedStudent(studentDto);
		
		model.addAttribute("message","testing");
		return "redirect:/index";
	}
	
	@GetMapping("/student/delete")
	public String deleteStudent(Model model, @RequestParam("id") int studentId){
		
		studentService.deleteStudentById(studentId);
		model.addAttribute("message","Student deleted sucessfully");
		
		return "redirect:/index";
	}
	
}
