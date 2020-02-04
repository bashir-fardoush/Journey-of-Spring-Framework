package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fardoushlab.mavenspring.config.HibernateConfig;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;
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
	
	public void saveEditedStudent(Student student){
		
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
			
			//session.save(student);			
			session.update(student);
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
		
		/******CRITERIA QUERY IMPLEMENTATION*****/
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> root = studentCriteriaQuery.from(Student.class);
		
		studentCriteriaQuery.where(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
		studentCriteriaQuery.select(root);
		
		var query = session.getEntityManagerFactory()
				.createEntityManager()
				.createQuery(studentCriteriaQuery);
		
		
		/*var query = session
				.getEntityManagerFactory()
				.createEntityManager().createQuery("select s from Student s where name=: sname",Student.class);
		query.setParameter("sname", name);*/
		
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
	
	public Student getStudentById(int studentId){
		
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if(!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> root = studentCriteriaQuery.from(Student.class);
		
		studentCriteriaQuery.where(criteriaBuilder.equal(root.get("id"), studentId));
		studentCriteriaQuery.select(root);
		
		var query = session.getEntityManagerFactory()
				.createEntityManager()
				.createQuery(studentCriteriaQuery);
		
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
		
		return Optional.ofNullable(studentList.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with this id"));
		
		
	}
	
	public List<Student> getStudentList(){
		
		var session = hibernateConfig.getSession();
		var tran = session.beginTransaction();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class); 
		Root<Student> root = studentCriteriaQuery.from(Student.class);
		
		studentCriteriaQuery.select(root);
		
		var query = session.getEntityManagerFactory().createEntityManager()
				.createQuery(studentCriteriaQuery);
		
		
		//var query = session.createQuery("From Student", Student.class);
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
	
	
	public void deleteStudentById(int studentId) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();		

	
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();	
		CriteriaDelete<Student> scd = criteriaBuilder.createCriteriaDelete(Student.class);
		Root<Student> root = scd.from(Student.class);
		scd.where(criteriaBuilder.equal(root.get("id"), studentId));
			
		
		var query = session.createQuery(scd);
	
		try {			
			query.executeUpdate();
			 
		}catch(HibernateException e) {
			
			if(transection!= null ) {
				transection.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}
	
	}
	
}
