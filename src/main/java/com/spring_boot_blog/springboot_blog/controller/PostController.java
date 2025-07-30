package com.spring_boot_blog.springboot_blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_boot_blog.springboot_blog.dto.PostRequest;
import com.spring_boot_blog.springboot_blog.dto.PostResponse;
import com.spring_boot_blog.springboot_blog.service.PostService;

@RestController
@RequestMapping("/blog/post")
public class PostController {
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	PostService service;
	
	@PostMapping("/create/user/{userId}/category/{categoryId}")
	PostResponse createPost(@RequestParam("image") MultipartFile file, @RequestParam("postRequest") String postRequest,
			@PathVariable Long userId, @PathVariable Long categoryId) throws IOException {
	  PostRequest request =	mapper.readValue(postRequest, PostRequest.class);
	  PostResponse response = service.createPost(file, request, userId, categoryId);
	  return response;
	}

	@GetMapping("/getPostById/{id}")
	PostResponse  getPostById(@PathVariable Long id) {
		return service.getPostById(id);
	}
	
	@PutMapping("/updatePost/{id}")
	PostResponse updatePost(@RequestBody PostRequest request,@PathVariable Long id) {
		return service.updatePost(request, id);
	}
	
	@DeleteMapping("/deletePost/{id}")
	String deletePost(@PathVariable Long id) {
		return service.deletePost(id);
	}
	
	@GetMapping("/getAllPost")
	List<PostResponse> getAllPost(){
		return service.getAllPost();
	}
}
