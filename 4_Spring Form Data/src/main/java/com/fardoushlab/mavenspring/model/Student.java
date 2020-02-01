package com.fardoushlab.mavenspring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student implements Serializable  {

	@Id
	@GeneratedValue(strategy  = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private int age;
	//private List<Course> courses;
	private String gender;
	private String email;
	//private List<Country> country;
	
	
	public Student() {
		
	}


	public Student(long id, String name, int age,/* List<Course> courses,*/ String gender, String email/*,
			List<Country> country*/) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		//this.courses = courses;
		this.email = email;
		this.gender = gender;
		
	//	this.country = country;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

/*
	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}*/


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", email=" + email
				+ "]";
	}

/*
	public List<Country> getCountry() {
		return country;
	}


	public void setCountry(List<Country> country) {
		this.country = country;
	}*/

/*
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", courses=" + courses + ", gender=" + gender
				+ ", email=" + email + ", country=" + country + "]";
	}*/
	
	
	
	
	
	
	
	
}
