package com.spring_boot_blog.springboot_blog.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring_boot_blog.springboot_blog.dto.PostRequest;
import com.spring_boot_blog.springboot_blog.dto.PostResponse;

public interface PostService {

	public PostResponse createPost(MultipartFile file, PostRequest request, Long userId, Long categoryId)
			throws IOException;

	public PostResponse getPostById(Long postId);

	public PostResponse updatePost(PostRequest request, Long postId);

	public String deletePost(Long postId);

	public List<PostResponse> getAllPost();
}
