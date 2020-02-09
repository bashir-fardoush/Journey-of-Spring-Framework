package com.fardoushlab.mavenspring.requestModels;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fardoushlab.mavenspring.model.Country;
import com.fardoushlab.mavenspring.model.Course;

public class Student implements Serializable{
	
	private long id;
	
	private String name;	
	
	private int age;	
	
	private String gender;
	
	private String email;
	
	private String countryCode;
	
	private List<String> courseCodes;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Student(long id, String name, int age, String gender, String email, String countryCode,
			List<String> courseCodes) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.countryCode = countryCode;
		this.courseCodes = courseCodes;
	}


	public Student(String name, int age, String gender, String email, String countryCode, List<String> courseCodes) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.countryCode = countryCode;
		this.courseCodes = courseCodes;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<String> getCourseCodes() {
		return courseCodes;
	}

	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}
	
	
}
