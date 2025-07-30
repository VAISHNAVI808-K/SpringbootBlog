package com.spring_boot_blog.springboot_blog.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
	private String name;
	private String address;
	private String gender;
	private Integer age;
	private Date createdDate;
	private Date updatedDate;
	@Column(length=10)
	private Long mobileNo;
	private String about;
	@JsonFormat(shape=Shape.STRING,pattern = "dd-mm-yyyy")
	private Date dob;

}
