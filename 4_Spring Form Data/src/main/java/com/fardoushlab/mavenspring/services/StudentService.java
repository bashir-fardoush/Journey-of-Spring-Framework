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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fardoushlab.mavenspring.config.persistance.HibernateConfig;
import com.fardoushlab.mavenspring.dtos.StudentBasicDto;
import com.fardoushlab.mavenspring.dtos.StudentDto;
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
		
	
	public void addStudent(StudentDto studentDto) {
		
		var country = countryService.getCountryByCode(studentDto.getCountryCode());
		
		var courseList = new ArrayList<Course>();
		
		for (String courseCode : studentDto.getCourseCodes()) {
			var course = courseService.getCourseByCourseCode(courseCode);
			courseList.add(course);
		}
		
		var studentEntity = new Student();
		
		BeanUtils.copyProperties(studentDto, studentEntity);		
		studentEntity.setCountry(country);
		studentEntity.setCourses(courseList);
	
		var session = hibernateConfig.getSession();
		var trans =  session.getTransaction();
		
		if(!trans.isActive()) {
			trans = session.beginTransaction();
		}
		
		try {
			
			session.save(studentEntity);			
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
	
	public void saveEditedStudent(StudentDto studentDto){
		
		var country = countryService.getCountryByCode(studentDto.getCountryCode());
		
		var courseList = new ArrayList<Course>();
		
		for (String courseCode : studentDto.getCourseCodes()) {
			var course = courseService.getCourseByCourseCode(courseCode);
			courseList.add(course);
		}
		var student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		
		student.setCountry(country);
		student.setCourses(courseList);
	
		var session = hibernateConfig.getSession();
		var trans =  session.getTransaction();
		
		if(!trans.isActive()) {
			trans = session.beginTransaction();
		}
		
		try {						
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
	
	public List<StudentBasicDto> searchStudentByName(String name){
		
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
		var studentBasicDtoList = new ArrayList<StudentBasicDto>();
		
		try {
			
			studentList = (ArrayList<Student>) query.getResultList();
			studentList.forEach(student->{
				
				var dto = new StudentBasicDto();				
				BeanUtils.copyProperties(student, dto);
				studentBasicDtoList.add(dto);
								
			});
			
		}catch(HibernateException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return studentBasicDtoList;
	}
	
	
	public StudentDto getStudentById(int studentId){
		
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
		
		if(studentList.get(0) != null) {
			
			StudentDto dto = new StudentDto();
			BeanUtils.copyProperties(studentList.get(0), dto);
			
			dto.setCountryCode(studentList.get(0).getCountry().getCountryCode());
	//	List<Course> courses = 	studentList.get(0).getCourses();
			List<String>courseCodes = new ArrayList<String>();
			
			studentList.get(0).getCourses().forEach(course->{				
				courseCodes.add(course.getCourseCode());
			});
			dto.setCourseCodes(courseCodes);
			
			return dto;
		}else {
			 throw new ResourceNotFoundException("Student not found with this id");
		}
		
		/*return Optional.ofNullable(studentList.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with this id"));
		*/
		
	}
	
	public List<StudentBasicDto> getStudentList(){
		
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
		var studentBasicDtoList  = new ArrayList<StudentBasicDto>();
		try {
			studentList = 	(ArrayList<Student>) query.getResultList();	
		}catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		studentList.forEach(student->{
			var dto = new StudentBasicDto();
			
			BeanUtils.copyProperties(student, dto);
			studentBasicDtoList.add(dto);
			
		});
		
		//return studentList;
		return studentBasicDtoList;
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
