package com.spring_boot_blog.springboot_blog.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentResponse {

	private Long commentId;
	private String comment;
	private Date createdDate;
	private Date updatedDate;
	private UserResponse user;
}
