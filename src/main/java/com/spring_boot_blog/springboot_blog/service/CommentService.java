package com.spring_boot_blog.springboot_blog.service;

import java.util.List;

import com.spring_boot_blog.springboot_blog.dto.CommentRequest;
import com.spring_boot_blog.springboot_blog.dto.CommentResponse;

public interface CommentService {

	public CommentResponse createComment(CommentRequest request, Long postId, Long userId);

	public CommentResponse getCommentById(Long commentId);

	public CommentResponse updateComment(CommentRequest request, Long commentId);

	public String deleteComment(Long commentId);

	public List<CommentResponse> getAllComment();
}
