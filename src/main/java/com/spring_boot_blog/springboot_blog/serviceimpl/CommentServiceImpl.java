package com.spring_boot_blog.springboot_blog.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot_blog.springboot_blog.dto.CommentRequest;
import com.spring_boot_blog.springboot_blog.dto.CommentResponse;
import com.spring_boot_blog.springboot_blog.dto.UserResponse;
import com.spring_boot_blog.springboot_blog.entity.CommentEntity;
import com.spring_boot_blog.springboot_blog.entity.PostEntity;
import com.spring_boot_blog.springboot_blog.entity.UserEntity;
import com.spring_boot_blog.springboot_blog.exception.ObjectShouldNotBeNull;
import com.spring_boot_blog.springboot_blog.exception.ResourceNotFoundException;
import com.spring_boot_blog.springboot_blog.repository.CommentRepository;
import com.spring_boot_blog.springboot_blog.repository.PostRepository;
import com.spring_boot_blog.springboot_blog.repository.UserRepository;
import com.spring_boot_blog.springboot_blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public CommentResponse createComment(CommentRequest request, Long postId, Long userId) {
		if(Objects.nonNull(userId) && Objects.nonNull(postId)) {
			UserEntity userEntity = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User is not found in database, Please register User..!"));
			PostEntity postEntity = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post is not found in database, please create post...!"));
			CommentEntity entity = new CommentEntity();
			entity.setComment(request.getComment());
			entity.setUser(userEntity);
			entity.setPost(postEntity);
			CommentEntity commentEntity = repository.save(entity);
			CommentResponse response = convertCommentEntityToResponse(commentEntity);
			return response;
		}else {
			throw new ObjectShouldNotBeNull("User or Post Should not be null..!");
		}
	}

	@Override
	public CommentResponse getCommentById(Long commentId) {
		if(Objects.nonNull(commentId)) {
			CommentEntity commentEntity = repository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment is not found in database,please enter comment...!"));
			CommentResponse response = convertCommentEntityToResponse(commentEntity);
			return response;
		}else {
			throw new ObjectShouldNotBeNull("Comment should not be null...!");
		}
	}

	@Override
	public CommentResponse updateComment(CommentRequest request, Long commentId) {
		if(Objects.nonNull(commentId)) {
			CommentEntity commentEntity = repository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment not found in database, Please enter comment...!"));
			commentEntity.setComment(request.getComment());
			CommentEntity commentEntity2 = repository.save(commentEntity);
			CommentResponse response = convertCommentEntityToResponse(commentEntity2);
			return response;
		}else {
			throw new ObjectShouldNotBeNull("Comment Should Not Be Null...!");
		}
	}

	@Override
	public String deleteComment(Long commentId) {
		if(Objects.nonNull(commentId)) {
			CommentEntity commentEntity = repository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment not found in database, Please enter comment...!"));
			repository.delete(commentEntity);
			return "Comment is Deleted...!";
		}else {
			throw new ObjectShouldNotBeNull("Comment should not be null..!");
		}
	}

	@Override
	public List<CommentResponse> getAllComment() {
		List<CommentEntity> list = repository.findAll();
		List<CommentResponse> commentResponses = new ArrayList<>();
		for(CommentEntity entity : list) {
			CommentResponse response = convertCommentEntityToResponse(entity);
			commentResponses.add(response);
		}
		return commentResponses;
	}
	
	private CommentResponse convertCommentEntityToResponse(CommentEntity entity) {
		CommentResponse response = new CommentResponse();
		response.setCommentId(entity.getCommentId());
		response.setComment(entity.getComment());
		response.setCreatedDate(entity.getCreatedDate());
		response.setUpdatedDate(entity.getUpdatedDate());
		UserEntity userEntity = entity.getUser();
		UserResponse userResponse = convertUserEntityToResponse(userEntity);
		response.setUser(userResponse);
		return response;
	}
	
	private UserResponse convertUserEntityToResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setUserId(entity.getUserId());
		response.setAbout(entity.getAbout());
		response.setAddress(entity.getAddress());
		response.setAge(entity.getAge());
		response.setCreatedDate(entity.getCreatedDate());
		response.setDob(entity.getDob());
		response.setGender(entity.getGender());
		response.setMobileNo(entity.getMobileNo());
		response.setName(entity.getName());
		return response;
	}

}
