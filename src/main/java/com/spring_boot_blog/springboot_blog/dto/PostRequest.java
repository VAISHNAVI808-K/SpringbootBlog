package com.spring_boot_blog.springboot_blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequest {

	private String title;
	private String postAbout;
	private String postImage;
}
