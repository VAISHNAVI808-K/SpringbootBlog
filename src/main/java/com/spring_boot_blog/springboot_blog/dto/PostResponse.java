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
public class PostResponse {

	private Long postId;
	private String title;
	private String postAbout;
	private String postImage;
	private Date createDate;
	private Date updatedDate;
	private UserResponse user;

	private CategoryResponse category;

	List<CommentResponse> comment;
}
