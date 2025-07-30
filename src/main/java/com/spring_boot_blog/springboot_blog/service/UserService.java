package com.spring_boot_blog.springboot_blog.service;

import java.util.List;

import com.spring_boot_blog.springboot_blog.dto.UserRequest;
import com.spring_boot_blog.springboot_blog.dto.UserResponse;

public interface UserService {

	public UserResponse createUser(UserRequest request);

	public UserResponse getUserById(Long id);

	public UserResponse updateUser(UserRequest request, Long id);

	public String deleteUser(Long id);

	public List<UserResponse> getAllUser();
}
