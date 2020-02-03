package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fardoushlab.mavenspring.config.HibernateConfig;
import com.fardoushlab.mavenspring.model.Country;
import com.fardoushlab.mavenspring.model.Course;
import com.fardoushlab.mavenspring.model.Student;
@Service
public class StudentService {
	
	private HibernateConfig hibernateConfig;

	@Autowired
	CountryService countryService;
	
	@Autowired
	CourseService courseService;
	
	
	public StudentService(HibernateConfig hibernateConfig) {
		super();
		this.hibernateConfig = hibernateConfig;
	}
	
	
	
	
	public void addStudent(Student student) {
		
		var country = countryService.getCountryByCode(student.getCountry().getCountryCode());
		
		var courseList = new ArrayList<Course>();
		
		for (String courseCode : student.getCourseCodes()) {
			var course = courseService.getCourseByCourseCode(courseCode);
			courseList.add(course);
		}
		
		student.setCountry(country);
		student.setCourses(courseList);
	
		var session = hibernateConfig.getSession();
		var trans =  session.getTransaction();
		
		if(!trans.isActive()) {
			trans = session.beginTransaction();
		}
		
		try {
			
			session.save(student);			
			trans.commit();
			
		}catch(HibernateException e) {
			if(trans!= null) {
				
				trans.rollback();
			}
			
			e.printStackTrace();
		}finally {
			session.close();
		}	
		
		
	}
	
	public List<Student> searchStudentByName(String name){
		
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if(!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		
		var query = session
				.getEntityManagerFactory()
				.createEntityManager().createQuery("select s from Student s where name=: sname",Student.class);
		query.setParameter("sname", name);
		
		var studentList= new ArrayList<Student>();
		
		try {
			
			studentList = (ArrayList<Student>) query.getResultList();
			
		}catch(HibernateException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return studentList;
	}
	
	public List<Student> getStudentList(){
		
		var session = hibernateConfig.getSession();
		var tran = session.beginTransaction();
		
		var query = session.createQuery("From Student", Student.class);
		var studentList= new ArrayList<Student>();
		try {
			studentList = 	(ArrayList<Student>) query.getResultList();	
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return studentList;
	}
	
	
}
