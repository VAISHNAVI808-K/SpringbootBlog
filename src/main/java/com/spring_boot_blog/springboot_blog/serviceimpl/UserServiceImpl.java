package com.spring_boot_blog.springboot_blog.serviceimpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot_blog.springboot_blog.dto.UserRequest;
import com.spring_boot_blog.springboot_blog.dto.UserResponse;
import com.spring_boot_blog.springboot_blog.entity.UserEntity;
import com.spring_boot_blog.springboot_blog.exception.ObjectShouldNotBeNull;
import com.spring_boot_blog.springboot_blog.exception.ResourceNotFoundException;
import com.spring_boot_blog.springboot_blog.repository.UserRepository;
import com.spring_boot_blog.springboot_blog.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepository repository;
	@Override
	public UserResponse createUser(UserRequest request) {
		UserEntity entity = convertUserRequestToEntity(request);
		UserEntity save = repository.save(entity);
		UserResponse response = convertUserEntityToResponse(save);
		return response;
	}

	@Override
	public UserResponse getUserById(Long id) {
		if(Objects.nonNull(id)) {
			UserEntity userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found in database, please register first"));
		    UserResponse response = convertUserEntityToResponse(userEntity);
		    return response;
		}else {
			throw new ObjectShouldNotBeNull("User Id should not be null..!");
		}
	}

	@Override
	public UserResponse updateUser(UserRequest request, Long id) {
		if(Objects.nonNull(id)) {
			UserEntity userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found in database, please register first"));
            userEntity.setName(request.getName());
            userEntity.setAbout(request.getAbout());
            userEntity.setAddress(request.getAddress());
            userEntity.setAge(request.getAge());
            userEntity.setDob(request.getDob());
            userEntity.setGender(request.getGender());
            userEntity.setMobileNo(request.getMobileNo());
            UserEntity save = repository.save(userEntity);
            UserResponse response = convertUserEntityToResponse(save);
            return response;
		}else {
			throw new ObjectShouldNotBeNull("User Id should not be null..!");

		}
		
	}

	@Override
	public String deleteUser(Long id) {
		if(Objects.nonNull(id)) {
			UserEntity userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found..!"));
            repository.delete(userEntity);
            return "User is deleted";
		}else {
			throw new RuntimeException("User Not Deleted..!");
		}

	}

	@Override
	public List<UserResponse> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private UserEntity convertUserRequestToEntity(UserRequest request) {
		UserEntity entity = new UserEntity();
		entity.setName(request.getName());
		entity.setAbout(request.getAbout());
		entity.setAddress(request.getAddress());
		entity.setAge(request.getAge());
		entity.setDob(request.getDob());
		entity.setGender(request.getGender());
		entity.setMobileNo(request.getMobileNo());
		return entity;
	}
	
	private UserResponse convertUserEntityToResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setName(entity.getName());
		response.setAddress(entity.getAddress());
		response.setUserId(entity.getUserId());
		response.setAbout(entity.getAbout());
		response.setDob(entity.getDob());
		response.setCreatedDate(entity.getCreatedDate());
		response.setGender(entity.getGender());
		response.setMobileNo(entity.getMobileNo());
		response.setUpdatedDate(entity.getUpdatedDate());
		return response;
	}

}
