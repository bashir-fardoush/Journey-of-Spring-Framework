package com.fardoushlab.springpractice;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



public class AppInitializer implements WebApplicationInitializer  {

	public void onStartup(ServletContext servletContext) throws ServletException {
	
		
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(AppRootConfig.class);
		ac.refresh();
			
		servletContext.addListener(new ContextLoaderListener(ac));
		
		AnnotationConfigWebApplicationContext servCtx = new AnnotationConfigWebApplicationContext();
		servCtx.register(ServletConfig.class);
		
		ServletRegistration.Dynamic register =servletContext.addServlet("app",new DispatcherServlet(servCtx));
		register.setLoadOnStartup(1);
		register.addMapping("/");
		
	}

}
