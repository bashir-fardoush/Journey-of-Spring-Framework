package com.fardoushlab.mavenspring.model;

public class Course {
	
	private int id;
	private String courseCode;
	private String courseName;
	
	
	public Course() {
		super();
	}


	public Course(int id, String courseCode, String courseName) {
		super();
		this.id = id;
		this.courseCode = courseCode;
		this.courseName = courseName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCourseCode() {
		return courseCode;
	}


	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", courseCode=" + courseCode + ", courseName=" + courseName + "]";
	}
	
	
	

}
