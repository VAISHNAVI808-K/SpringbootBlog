package com.spring_boot_blog.springboot_blog.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {

	private String msg;
	private HttpStatus statusCode;
	private LocalDateTime date;
}
