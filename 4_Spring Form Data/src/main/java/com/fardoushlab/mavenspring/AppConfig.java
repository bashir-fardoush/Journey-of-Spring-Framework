package com.fardoushlab.mavenspring;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.fardoushlab.mavenspring.config.persistance.HibernateConfig;
import com.fardoushlab.mavenspring.services.CountryService;
import com.fardoushlab.mavenspring.services.CourseService;

@Configuration
@ComponentScan(basePackages = {
		"com.fardoushlab.mavenspring.config.persistance",
		"com.fardoushlab.mavenspring.config.security",
		"com.fardoushlab.mavenspring.services",
		})
public class AppConfig {
	
	/*@Bean
	public CountryService getCountryService() {
		return new CountryService();
	}*/
	
	/*@Bean
	public CourseService getCourseService() {
		return new CourseService();
	}*/
	
	@Bean
	public HibernateConfig getHibernateConfig() {
		return new HibernateConfig();
	}
	
	@Bean
	public GlobalExceptionHandler getGlobalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

    

}
