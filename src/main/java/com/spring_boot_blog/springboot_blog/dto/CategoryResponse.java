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
public class CategoryResponse {

	private Long categoryId;
	private String category;
	private Date createdDate;
	private Date updatedDate;
	List<PostResponse> post;
	
}
