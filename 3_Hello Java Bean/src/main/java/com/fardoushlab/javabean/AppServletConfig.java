package com.fardoushlab.javabean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.fardoushlab.javabean.Controllers"})
public class AppServletConfig implements WebMvcConfigurer {

	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		registry.jsp("/WEB-INF/views/",".jsp");
	}
}
