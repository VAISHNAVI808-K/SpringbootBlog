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

import com.spring_boot_blog.springboot_blog.dto.CategoryRequest;
import com.spring_boot_blog.springboot_blog.dto.CategoryResponse;
import com.spring_boot_blog.springboot_blog.service.CategoryService;

@RestController
@RequestMapping("/blog/category")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@PostMapping("/register")
	CategoryResponse registerCategory(@RequestBody CategoryRequest request) {
		return service.registerCategory(request);
	}
	
	@GetMapping("/getById/{Id}")
	CategoryResponse getCategoryById(@PathVariable Long id) {
		return service.getCategoryById(id);
	}
	
//	@GetMapping("/getByName/{name}")
//	List<CategoryResponse> getByName(@PathVariable String name){
//		return service.getByName(name);
//	}
	
	@PutMapping("/updateCategory/{id}")
	CategoryResponse updateCategory(@RequestBody CategoryRequest request,@PathVariable Long id ) {
		return service.updateCategory(request, id);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	String deleteCategory(@PathVariable Long id) {
		return service.deleteCategory(id);
	}
	
	@GetMapping("/getAllCategory")
	List<CategoryResponse> getAllCategory(){
		return service.getAllCategory();
	}
}
