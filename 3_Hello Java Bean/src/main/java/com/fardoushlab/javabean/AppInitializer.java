package com.fardoushlab.javabean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	
		//application root configuration
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(AppRootConfig.class);
		ac.refresh();
		
		servletContext.addListener(new ContextLoaderListener(ac));
		
		AnnotationConfigWebApplicationContext servletConfigCtx = new AnnotationConfigWebApplicationContext();
		servletConfigCtx.register(AppServletConfig.class);
		
		ServletRegistration.Dynamic  registerer =  servletContext.addServlet("app_servlet", new DispatcherServlet(servletConfigCtx));
		registerer.setLoadOnStartup(1);
		registerer.addMapping("/");
		
		
	}

}
