package com.fardoushlab.mavenspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fardoushlab.mavenspring.services.CountryService;

@Configuration
public class AppConfig {
	
	@Bean
	public CountryService getCountryService() {
		return new CountryService();
	}
	
	@Bean
	public GlobalExceptionHandler getGlobalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

}
