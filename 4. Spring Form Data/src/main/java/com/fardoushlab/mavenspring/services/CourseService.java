package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;

import com.fardoushlab.mavenspring.exception.ResourceAlreadyExistsException;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;
import com.fardoushlab.mavenspring.model.Course;

public class CourseService {

	private static List<Course> courses = new ArrayList<Course>();
	
	private static final String[] COURSE_LIST = new String[] {
			"Introduction of computer Science","Analog Electronics","Analog Electronics (Lab)"};
	
	private static final String[] COURSE_CODE_LIST = new String[] {
			"CSE 111","CSE 112","CSE 113"};

	public CourseService() {
		super();
		
		for(int i = 0; i < COURSE_LIST.length; i++) {
			addCourse(COURSE_CODE_LIST[i], COURSE_LIST[i]);
		}
	
	}
	
	
	private void addCourse(String courseCode, String courseName) {
		var courseObj = new Course();
		courseObj.setId(courses.size()+1);
		courseObj.setCourseCode(courseCode);
		courseObj.setCourseName(courseName);
		courses.add(courseObj);
	}
	
	
	public void addCourse(Course course) {	
		checkCourseInList(course);
		course.setId(courses.size()+1);
		courses.add(course);
	}
	
	private void checkCourseInList(Course c) {
	
		if(courses
				.stream()
				.filter(course-> course.getCourseCode().equals(c.getCourseCode()))
				.findAny().isPresent()) {
			throw new ResourceAlreadyExistsException("Course already exists");
			
		}
		
		
		
		
	}
	
	public Course getCourseById(String courseCode) {
		
		return courses
			.stream()
			.filter(course->course.getCourseCode().equals(courseCode) )
			.findAny()
			.orElseThrow(() -> new ResourceNotFoundException("No course found with this code"));
	}
	
	public List<Course> getAllCourses(){
		return courses;
	}
	
	
	
	
	
}
