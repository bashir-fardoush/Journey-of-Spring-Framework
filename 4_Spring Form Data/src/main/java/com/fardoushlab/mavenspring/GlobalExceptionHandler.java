package com.fardoushlab.mavenspring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fardoushlab.mavenspring.exception.ResourceAlreadyExistsException;
import com.fardoushlab.mavenspring.exception.ResourceNotFoundException;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public String  handleConflict(HttpServletRequest req,  Exception e, Model model) {
		
		model.addAttribute("message",e.getMessage());		
		e.printStackTrace();
		return "error";
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException(HttpServletRequest req,  Exception e, Model model) {
		
		model.addAttribute("message",e.getMessage());
		e.printStackTrace();
		return "error";
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public String  handleRuntimeExcepton(HttpServletRequest req,  Exception e, Model model) {
		
		model.addAttribute("message",e.getMessage());	
		e.printStackTrace();
		return "error";
	}

}
