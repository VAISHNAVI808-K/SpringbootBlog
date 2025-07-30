package com.spring_boot_blog.springboot_blog.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring_boot_blog.springboot_blog.dto.CategoryResponse;
import com.spring_boot_blog.springboot_blog.dto.CommentResponse;
import com.spring_boot_blog.springboot_blog.dto.PostRequest;
import com.spring_boot_blog.springboot_blog.dto.PostResponse;
import com.spring_boot_blog.springboot_blog.dto.UserResponse;
import com.spring_boot_blog.springboot_blog.entity.CategoryEntity;
import com.spring_boot_blog.springboot_blog.entity.CommentEntity;
import com.spring_boot_blog.springboot_blog.entity.PostEntity;
import com.spring_boot_blog.springboot_blog.entity.UserEntity;
import com.spring_boot_blog.springboot_blog.exception.ObjectShouldNotBeNull;
import com.spring_boot_blog.springboot_blog.exception.ResourceNotFoundException;
import com.spring_boot_blog.springboot_blog.repository.CategoryRepository;
import com.spring_boot_blog.springboot_blog.repository.PostRepository;
import com.spring_boot_blog.springboot_blog.repository.UserRepository;
import com.spring_boot_blog.springboot_blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Value("${project.image}")
	String path;
	
	@Autowired
	private PostRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PostResponse createPost(MultipartFile file, PostRequest request, Long userId, Long categoryId)
			throws IOException {
		if(Objects.nonNull(categoryId) && Objects.nonNull(userId)) {
			UserEntity userEntity = userRepository.findById(userId).orElseThrow(
					() -> new ResourceNotFoundException("User not found in database. Please register again."));
			CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException("Category not found in database, please create first...!!!"));
           
			PostEntity entity = new PostEntity();
			String imageName = uploadImageInFolder(file,path);
			entity.setTitle(request.getTitle());
			entity.setPostAbout(request.getPostAbout());
			entity.setPostImage(imageName);
			entity.setUser(userEntity);
			entity.setCategory(categoryEntity);
			PostEntity postEntity = repository.save(entity);
			PostResponse response = convertPostEntityToResponse(postEntity);
			return response;
		}else {
			throw new ObjectShouldNotBeNull("User id or Category id should not be null");
		}
	}

	@Override
	public PostResponse getPostById(Long postId) {
		if(Objects.nonNull(postId)) {
			PostEntity postEntity = repository.findById(postId).orElseThrow(
					() -> new ResourceNotFoundException("Post is not found in database,please create post.."));
			PostResponse response = convertPostEntityToResponse(postEntity);
			return response;
		}else {
			throw new ObjectShouldNotBeNull("post should not be empty..!");
		}
	}

	@Override
	public PostResponse updatePost(PostRequest request, Long postId) {
		if(Objects.nonNull(postId)) {
			PostEntity postEntity = repository.findById(postId).orElseThrow(
					() -> new ResourceNotFoundException("Post is not found in database,please create the post..!"));
			postEntity.setTitle(request.getTitle());
			postEntity.setPostAbout(request.getPostAbout());
			postEntity.setPostImage(request.getPostImage());
			PostEntity save = repository.save(postEntity);
			PostResponse postResponse = convertPostEntityToResponse(save);
			return postResponse;
		}else {
			throw new ObjectShouldNotBeNull("Post Should not be null");
		}
	}

	@Override
	public String deletePost(Long postId) {
		if(Objects.nonNull(postId)) {
			PostEntity entity = repository.findById(postId).orElseThrow(
					() -> new ResourceNotFoundException("Post is not found in database,please create the post..!"));
			repository.delete(entity);
			return "Post is Deleted..!";
		}else {
			throw new RuntimeException("Post is not deleted..!");
		}
	}

	@Override
	public List<PostResponse> getAllPost() {
          List<PostEntity> list = repository.findAll();
          List<PostResponse> responseList = new ArrayList<>();
          for(PostEntity entity : list) {
        	  PostResponse response = convertPostEntityToResponse(entity);
        	  responseList.add(response);
          }
		return responseList;
	}

	private PostResponse convertPostEntityToResponse(PostEntity entity) {
		PostResponse response = new PostResponse();
		response.setPostId(entity.getPostId());
		response.setPostAbout(entity.getPostAbout());
		response.setTitle(entity.getTitle());
		response.setPostImage(entity.getPostImage());
		response.setCreateDate(entity.getCreatedDate());
		response.setUpdatedDate(entity.getUpdatedDate());
		
		UserEntity userEntity = entity.getUser();
		UserResponse userResponse = convertUserEntityToResponse(userEntity);
		response.setUser(userResponse);
		
		CategoryEntity categoryEntity = entity.getCategory();
		CategoryResponse categoryResponse = convertCategoryEntityToResponse(categoryEntity);
		response.setCategory(categoryResponse);
		
		List<CommentEntity> commentList = entity.getComment();
		List<CommentResponse> list = new ArrayList<>();
		for(CommentEntity commentEntity: commentList) {
			CommentResponse commentResponse = convertCommentEntityToResponse(commentEntity);
			list.add(commentResponse);
		}
		response.setComment(list);
		return response;
	}
	
	private UserResponse convertUserEntityToResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setUserId(entity.getUserId());
		response.setAbout(entity.getAbout());
		response.setAddress(entity.getAddress());
		response.setAge(entity.getAge());
		response.setDob(entity.getDob());
		response.setGender(entity.getGender());
		response.setMobileNo(entity.getMobileNo());
		response.setName(entity.getName());
		response.setCreatedDate(entity.getCreatedDate());
		response.setUpdatedDate(entity.getUpdatedDate());
		return response;
	}
	
	private CategoryResponse convertCategoryEntityToResponse(CategoryEntity entity) {
		CategoryResponse response = new CategoryResponse();
		response.setCategoryId(entity.getCategoryId());
		response.setCategory(entity.getCategory());
		response.setCreatedDate(entity.getCreatedDate());
		response.setUpdatedDate(entity.getUpdateDate());
		return response;
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
	
	String uploadImageInFolder(MultipartFile file,String path) throws IOException{
		String imageName = null;
		if(file != null) {
			String originalFileName = file.getOriginalFilename();
			String num = UUID.randomUUID().toString();
			imageName = originalFileName  + num;
			String fullFilePath = path + File.separator + imageName;
			
			File f = new File(path);
			if(!f.exists()) {
				f.mkdir();
			}
			Files.copy(file.getInputStream(), Paths.get(fullFilePath));
			return imageName;
		}else {
			return "";
		}
	}
}
