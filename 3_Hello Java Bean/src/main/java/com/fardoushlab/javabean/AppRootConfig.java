package com.fardoushlab.javabean;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;

import com.fardoushlab.javabean.model.User;
import com.fardoushlab.javabean.service.UserService;

@Configuration
//@ComponentScan(basePackages = {"com.fardoushlab.javabean.service"})
public class AppRootConfig {
	
	@Bean
	public User user() {
		return new User("Hasib", 0);
	}
	
	@Bean
	public UserService createUserService() {
		return new UserService(new ArrayList<User>());
	}

}
