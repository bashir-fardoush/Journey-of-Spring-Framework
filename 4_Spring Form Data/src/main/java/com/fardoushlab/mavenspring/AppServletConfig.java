package com.fardoushlab.mavenspring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.fardoushlab.mavenspring.controllers"})
public class AppServletConfig implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
	}
	
	
}
