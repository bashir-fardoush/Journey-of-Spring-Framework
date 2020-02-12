package com.fardoushlab.mavenspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fardoushlab.mavenspring.config.persistance.HibernateConfig;
import com.fardoushlab.mavenspring.exception.ResourceAlreadyExistsException;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;
import com.fardoushlab.mavenspring.model.Country;
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
	
		session.save(course);
		transection.commit();
		session.close();
	}
	
	
	private void checkCourseInDb(Course c) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Course> ccq = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = ccq.from(Course.class);
		
		ccq.where(criteriaBuilder.equal(root.get("courseCode"), c.getCourseCode()));
		var query = session.getEntityManagerFactory().createEntityManager()
				.createQuery(ccq);		
		
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
			session.update(c);	
			transection.commit();
		}catch(HibernateException e) {
			if(transection!= null) {
				transection.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}

	}
	

	public Course getCourseByCourseCode(String courseCode) {
		
		var session = hibernateConfig.getSession();
		var transection = session.beginTransaction();
		
		var query = session.getEntityManagerFactory()
				.createEntityManager()
				.createQuery("select c from com.fardoushlab.mavenspring.model.Course c where courseCode=: courseCode",Course.class);
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
	  
	  var query = session.getEntityManagerFactory()
			  .createEntityManager()
			  .createQuery("select c  from com.fardoushlab.mavenspring.model.Course c", Course.class);
	  var courseList = query.getResultList();	  
	  
	  session.close();
	  return courseList;
	  
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
