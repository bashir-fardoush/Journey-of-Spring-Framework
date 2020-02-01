package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fardoushlab.mavenspring.config.HibernateConfig;
import com.fardoushlab.mavenspring.exception.ResourceAlreadyExistsException;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;
import com.fardoushlab.mavenspring.model.Course;

@Service
public class CourseService {
	
	
	private HibernateConfig hibernateConfig;
	
	public CourseService(HibernateConfig hibernateConfig) {
		super();
		
		this.hibernateConfig = hibernateConfig;
		
	
	}
	
	/*private void addCourse(String courseCode, String courseName) {
		var courseObj = new Course();
		courseObj.setId(courses.size() + 1);
		courseObj.setCourseCode(courseCode);
		courseObj.setCourseName(courseName);
		courses.add(courseObj);
	}*/
	
	@Transactional
	public void addCourse(Course course) {	
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
	
		Random random = new Random();
		int randomInteger = random.nextInt();
		course.setId(randomInteger);
		
		session.save(course);
		transection.commit();
		session.close();
	}
	
	
	private void checkCourseInDb(Course c) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from com.fardoushlab.mavenspring.model.Course c where courseCode:= courseCode",Course.class);
		query.setParameter("courseCode", c.getCourseCode());
		
		if(query.getResultStream().findAny().isPresent()) {
		
			transection.rollback();
			session.close();
			throw new ResourceAlreadyExistsException("Course Already exists");
		}
		

		transection.commit();
		session.close();
		
		
	}
	

	
	public void saveEditedCourse (Course c) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		try {
			
		}catch(HibernateException e) {
			
		}

		session.update(c);		
		transection.commit();
		session.close();

	}
	
	/*public void deleteCourseByCourseCode(String courseCode) {
		for(int i = 0; i< courses.size(); i++) {
			if(courses.get(i).getCourseCode().equals(courseCode)) {
				courses.remove(i);
				break;
			}
		}
	}*/
	
	public Course getCourseByCourseCode(String courseCode) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from com.fardoushlab.mavenspring.model.Course c where courseCode=: courseCode",Course.class);
		query.setParameter("courseCode", courseCode);

	
		Course course =  query.getResultStream().findAny().orElseThrow(() ->{
		
			transection.commit();
			session.close();
			
			return new ResourceNotFoundException("Course not found");
		});
		
		transection.commit();
		session.close();
	
		return course;
		
		
	}

	
	public List<Course> getAllCourses(){
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();		
  		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from com.fardoushlab.mavenspring.model.Course c", Course.class);
		var courseList =  query.getResultList();
		
		transection.commit();
		session.close();
		
		return courseList;
		/*var courseList = new ArrayList<Course>();
		
		courseList.add(new Course(1,"CSE111" ,"P L 1"));
		courseList.add(new Course(2,"CSE112" ,"P L 2"));
		
		return courseList;*/
		
	}
	
	
	
	
	
	
}



/*


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

public Course getCourseByCourseCode(String courseCode) {
		
	return courses
		.stream()
		.filter(course->course.getCourseCode().equals(courseCode) )
		.findAny()
		.orElseThrow(() -> new ResourceNotFoundException("No course found with this code"));
}


public List<Course> getAllCourses(){
	return courses;
}

*/
