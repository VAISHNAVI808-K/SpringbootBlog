package com.spring_boot_blog.springboot_blog.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring_boot_blog.springboot_blog.dto.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandleer {

	@ExceptionHandler(ResourceNotFoundException.class)
	ExceptionResponse resourceNotFoundException(ResourceNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMsg(ex.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		response.setDate(LocalDateTime.now());
		return response;
	}
   
	@ExceptionHandler(ObjectShouldNotBeNull.class)
	ExceptionResponse objectShouldNotBeNull(ObjectShouldNotBeNull ex) {
		ExceptionResponse response= new ExceptionResponse();
		response.setMsg(ex.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		response.setDate(LocalDateTime.now());
		return response;
	}
}
