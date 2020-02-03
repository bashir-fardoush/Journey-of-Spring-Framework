package com.fardoushlab.mavenspring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course implements Serializable{
	
	@Id
	@GeneratedValue(strategy  = GenerationType.SEQUENCE)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="course_code", unique=true, updatable=false, nullable=false)
	private String courseCode;
	
	@Column(name="course_name")
	private String courseName;
	
	
	public Course() {
		
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
