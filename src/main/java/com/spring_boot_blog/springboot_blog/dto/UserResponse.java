package com.spring_boot_blog.springboot_blog.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

	private Long userId;
	private String name;
	private String address;
	private String gender;
	private Integer age;
	private Long mobileNo;
	private String about;
	private Date dob;
	private Date createdDate;
	private Date updatedDate;
	
	List<PostResponse> post;
}
