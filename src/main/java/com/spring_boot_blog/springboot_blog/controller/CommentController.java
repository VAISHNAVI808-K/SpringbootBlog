package com.spring_boot_blog.springboot_blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot_blog.springboot_blog.dto.CommentRequest;
import com.spring_boot_blog.springboot_blog.dto.CommentResponse;
import com.spring_boot_blog.springboot_blog.service.CommentService;

@RestController
@RequestMapping("/blog/comment")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@PostMapping("/addComment/{userId}/{postId}")
	CommentResponse addComment(@RequestBody CommentRequest request, @PathVariable Long userId, @PathVariable Long postId) {
		return service.createComment(request, postId, userId);
	}

	@GetMapping("/getCommentById/{Id}")
	CommentResponse getCommentById(@PathVariable Long id) {
		return service.getCommentById(id);
	}
	
	@PutMapping("/updateComment/{id}")
	CommentResponse updateComment(@RequestBody CommentRequest request,@PathVariable Long id) {
		return service.updateComment(request, id);
	}
	
	@DeleteMapping("/deleteComment/{id}")
	String deleteComment(@PathVariable Long id) {
		return service.deleteComment(id);
	}
	
	@GetMapping("/getAllComment")
	List<CommentResponse> getAllComment(){
		return service.getAllComment();
	}
}
