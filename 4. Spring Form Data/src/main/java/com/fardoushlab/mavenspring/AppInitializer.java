package com.fardoushlab.mavenspring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// app root configuration
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();
		
		servletContext.addListener(new ContextLoaderListener(ac));
		
		// servlet configuration		
		AnnotationConfigWebApplicationContext servletConfigCtx = new AnnotationConfigWebApplicationContext();
		servletConfigCtx.register(AppServletConfig.class);
		
		ServletRegistration.Dynamic registerer = servletContext.addServlet("app", new DispatcherServlet(servletConfigCtx));
		registerer.setLoadOnStartup(1);
		
		//handler mapping confiuration
		registerer.addMapping("/");
		
		
		
	}

}
