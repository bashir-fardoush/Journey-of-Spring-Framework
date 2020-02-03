package com.fardoushlab.mavenspring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.umd.cs.findbugs.annotations.CleanupObligation;

@Entity
@Table(name="student")
public class Student implements Serializable  {

	@Id
	@GeneratedValue(strategy  = GenerationType.SEQUENCE)
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="name")	
	private String name;
	
	@Column(name="age")
	private int age;
	
	@OneToMany(targetEntity = Course.class, cascade= {CascadeType.ALL})
	@JoinTable(name="student_course_map",
			joinColumns = {@JoinColumn(name="s_id")},
			inverseJoinColumns = {@JoinColumn(name="c_id")})
	private List<Course> courses;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne
	private Country  country;
	
	
	@Transient
	private List<String> courseCodes;
	
	public Student() {
		
	}
	public Student(long id, String name, int age, List<Course> courses, String gender, String email,
			Country country) {
		
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.courses = courses;
		this.email = email;
		this.gender = gender;		
		this.country = country;
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


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
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
	
	
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	public List<String> getCourseCodes() {
		return courseCodes;
	}
	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", courses=" + courses + ", gender=" + gender
				+ ", email=" + email + ", country=" + country + ", courseCodes=" + courseCodes + "]";
	}

	
	

	
	
	
	
	
	
	
}
