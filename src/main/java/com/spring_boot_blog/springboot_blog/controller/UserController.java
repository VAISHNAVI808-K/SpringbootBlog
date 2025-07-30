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

import com.spring_boot_blog.springboot_blog.dto.UserRequest;
import com.spring_boot_blog.springboot_blog.dto.UserResponse;
import com.spring_boot_blog.springboot_blog.service.UserService;

@RestController
@RequestMapping("/blog/user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/create")
	UserResponse createUser(@RequestBody UserRequest request) {
		return service.createUser(request);
	}
	
	@GetMapping("/getUserId/{id}")
	UserResponse getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}
	
	@PutMapping("/updateUser/{id}")
	UserResponse updateUser(@RequestBody UserRequest request,@PathVariable Long id) {
		return service.updateUser(request, id);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	String deleteUser(@PathVariable Long id) {
		return service.deleteUser(id);
	}
	
	@GetMapping("/getAllUser")
	List<UserResponse> getAllUser(){
		return service.getAllUser();
	}
}
