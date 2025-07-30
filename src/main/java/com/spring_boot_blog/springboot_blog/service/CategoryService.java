package com.spring_boot_blog.springboot_blog.service;

import java.util.List;

import com.spring_boot_blog.springboot_blog.dto.CategoryRequest;
import com.spring_boot_blog.springboot_blog.dto.CategoryResponse;

public interface CategoryService {

	public CategoryResponse registerCategory(CategoryRequest request);

	public CategoryResponse getCategoryById(Long categoryId);

	public CategoryResponse updateCategory(CategoryRequest request, Long categoryId);

	public String deleteCategory(Long categoryId);

	public List<CategoryResponse> getAllCategory();

//	public List<CategoryResponse> getByName(String name);
}
