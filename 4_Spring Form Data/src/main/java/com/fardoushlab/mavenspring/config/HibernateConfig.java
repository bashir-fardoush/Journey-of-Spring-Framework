package com.fardoushlab.mavenspring.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.Entity;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javassist.Modifier;


public class HibernateConfig {

	private SessionFactory sessionFactory = null;
	private Session session = null;
	
	public Session getSession() {
		this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();		
		return session != null ? this.session : createAndGetLocalSessionFactoryBean().openSession();
		
	}
	
	private SessionFactory createAndGetLocalSessionFactoryBean() {
		
		if(this.sessionFactory == null) {
			
			try {
				Configuration configuration = new Configuration();
				Properties settings = getProperties("hibernate.properties");
				configuration.setProperties(settings);
				configuration.addPackage("com.fardoushlab.mavenspring.model");
				
				for(Class<?> modelClass: (new Reflections("com.fardoushlab.mavenspring.model")).getTypesAnnotatedWith(Entity.class) ) {
				
					if(!Modifier.isAbstract(modelClass.getModifiers())) {					
						configuration.addAnnotatedClass(modelClass);
					}
					
				}
				
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
						.applySettings(settings);
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
			} catch (MappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	catch(Exception e) {
				e.printStackTrace();
			}		
			
		}		
		
		return sessionFactory;
	}
	
	
	private Properties getProperties(String propertyFileName) {
		
		Properties properties = new Properties();		
		InputStream input = Hibernate.class.getClassLoader().getResourceAsStream(propertyFileName);
		
		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties;
	}

	
	
}
