package com.spring_boot_blog.springboot_blog.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot_blog.springboot_blog.dto.CategoryRequest;
import com.spring_boot_blog.springboot_blog.dto.CategoryResponse;
import com.spring_boot_blog.springboot_blog.dto.CommentResponse;
import com.spring_boot_blog.springboot_blog.dto.PostResponse;
import com.spring_boot_blog.springboot_blog.dto.UserResponse;
import com.spring_boot_blog.springboot_blog.entity.CategoryEntity;
import com.spring_boot_blog.springboot_blog.entity.CommentEntity;
import com.spring_boot_blog.springboot_blog.entity.PostEntity;
import com.spring_boot_blog.springboot_blog.entity.UserEntity;
import com.spring_boot_blog.springboot_blog.exception.ObjectShouldNotBeNull;
import com.spring_boot_blog.springboot_blog.exception.ResourceNotFoundException;
import com.spring_boot_blog.springboot_blog.repository.CategoryRepository;
import com.spring_boot_blog.springboot_blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public CategoryResponse registerCategory(CategoryRequest request) {
		CategoryEntity entity = convertCategoryRequestToEntity(request);
		CategoryEntity save = repository.save(entity);
		CategoryResponse response = convertCategoryEntityToResponse(save);
		return response;
	}

	@Override
	public CategoryResponse getCategoryById(Long categoryId) {
		if (Objects.nonNull(categoryId)) {
			CategoryEntity entity = repository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException("Category is not found in database, please Create First"));
			CategoryResponse response = convertCategoryEntityToResponse(entity);
			return response;
		} else {
			throw new ObjectShouldNotBeNull("Category Id Should not be null...!!!");
		}

	}

	@Override
	public CategoryResponse updateCategory(CategoryRequest request, Long categoryId) {
		if (Objects.nonNull(categoryId)) {
			CategoryEntity entity = repository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException("Category is not found in database, please Create First"));
			entity.setCategory(request.getCategory());
			CategoryEntity save = repository.save(entity);
			CategoryResponse response = convertCategoryEntityToResponse(save);
			return response;
		} else {
			throw new ObjectShouldNotBeNull("Category Id should not be Null");
		}

	}

	@Override
	public String deleteCategory(Long categoryId) {
		if(Objects.nonNull(categoryId)) {
			CategoryEntity entity = repository.findById(categoryId).orElseThrow(					() -> new ResourceNotFoundException("Category is not found in database, please Create First"));
            repository.delete(entity);
            return "Category is Deleted..!";
		}else {
			throw new ObjectShouldNotBeNull("Category Id should Not be Null");
		}
	}

	@Override
	public List<CategoryResponse> getAllCategory() {
		List<CategoryEntity> list= repository.findAll();
		
		List<CategoryResponse> resList = new ArrayList<>();
		for(CategoryEntity entity : list) {
			CategoryResponse response = convertCategoryEntityToResponse(entity);
			resList.add(response);
		}
		return resList;
	}

//	@Override
//	public List<CategoryResponse> getByName(String name) {
//		List<CategoryResponse> resList = new ArrayList<>();
//		List<CategoryEntity> list = repository.findByName(name);
//		for(CategoryEntity entity : list) {
//			CategoryResponse response = convertCategoryEntityToResponse(entity);
//			resList.add(response);
//		}
//		return resList;
//	}

	private CategoryEntity convertCategoryRequestToEntity(CategoryRequest request) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCategory(request.getCategory());
		return entity;
	}

	private CategoryResponse convertCategoryEntityToResponse(CategoryEntity entity) {
		CategoryResponse response = new CategoryResponse();
		response.setCategoryId(entity.getCategoryId());
		response.setCategory(entity.getCategory());
		response.setCreatedDate(entity.getCreatedDate());
		response.setUpdatedDate(entity.getUpdateDate());

		List<PostEntity> post = entity.getPost();
		List<PostResponse> postResponses = new ArrayList<>();
		for (PostEntity postEntity : post) {
			PostResponse entityToResponse = convertPostEntityToResponse(postEntity);
			postResponses.add(entityToResponse);
		}
		response.setPost(postResponses);
		return response;
	}

	private PostResponse convertPostEntityToResponse(PostEntity entity) {
		PostResponse postResponse = new PostResponse();
		postResponse.setPostId(entity.getPostId());
		postResponse.setTitle(entity.getTitle());
		postResponse.setPostAbout(entity.getPostAbout());
		postResponse.setPostImage(entity.getPostImage());
		postResponse.setCreateDate(entity.getCreatedDate());
		postResponse.setUpdatedDate(entity.getUpdatedDate());

		UserEntity user = entity.getUser();
		UserResponse userEntityToResponse = convertUserEntityToResponse(user);
		postResponse.setUser(userEntityToResponse);

		List<CommentEntity> comment = entity.getComment();
		List<CommentResponse> listCommentResponse = new ArrayList<CommentResponse>();
		for (CommentEntity commentEntity : comment) {
			CommentResponse response = convertCommentEntityToResponse(commentEntity);
			listCommentResponse.add(response);
		}
		postResponse.setComment(listCommentResponse);

		CategoryEntity categoryEntity = entity.getCategory();
		CategoryResponse categoryResponse = convertCategoryEntityToResponse(categoryEntity);
		postResponse.setCategory(categoryResponse);

		return postResponse;
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

	private CommentResponse convertCommentEntityToResponse(CommentEntity entity) {
		CommentResponse response = new CommentResponse();
		response.setCommentId(entity.getCommentId());
		response.setComment(entity.getComment());
		response.setCreatedDate(entity.getCreatedDate());
		response.setUpdatedDate(entity.getUpdatedDate());

		UserEntity userEntity = entity.getUser();
		UserResponse response2 = convertUserEntityToResponse(userEntity);
		response.setUser(response2);
		return response;
	}
}
