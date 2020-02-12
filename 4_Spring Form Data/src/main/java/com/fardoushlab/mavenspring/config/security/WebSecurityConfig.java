 package com.fardoushlab.mavenspring.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	
		auth.inMemoryAuthentication().withUser("bashir").password("{noop}secret").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("saad").password("{noop}secret").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/images/**", "/css/**", "/js/**")
		.permitAll()

		.and()
			.authorizeRequests()
			.antMatchers("/course/add").hasRole("ADMIN")
			.antMatchers("/course/edit").hasRole("ADMIN")
			.antMatchers("/course/courses").hasAnyRole("ADMIN","USER")
			.antMatchers("/student/add").hasRole("ADMIN")
			.antMatchers("/student/edit").hasRole("ADMIN")
			.antMatchers("/index").hasAnyRole("ADMIN","USER")
			.antMatchers("/country/add").hasRole("ADMIN")
			.antMatchers("/country/edit").hasRole("ADMIN")
			.antMatchers("/country/countries").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login").loginProcessingUrl("/login-processing")
			.permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/index").failureUrl("/login?error=true")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
		
		
		
	}

	

	
}
