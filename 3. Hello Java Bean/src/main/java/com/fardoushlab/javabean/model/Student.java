package com.fardoushlab.javabean.model;

import java.io.Serializable;
import java.util.Set;
import java.util.List;



public class Student implements Serializable {
	
	public Student() {
		
	}
	
	private String name;
	private int age;
	private long id;
	private Set<Course> courses;
	private String gender;
	private List<Country> countryList;
	public Student(String name, int age, long id, Set<Course> courses, String gender, List<Country> countryList) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
		this.courses = courses;
		this.gender = gender;
		this.countryList = countryList;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", id=" + id + ", courses=" + courses + ", gender=" + gender
				+ ", countryList=" + countryList + "]";
	}
	
	
	
		
	
	
}
