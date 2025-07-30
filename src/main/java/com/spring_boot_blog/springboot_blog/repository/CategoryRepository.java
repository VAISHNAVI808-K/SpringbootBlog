package com.spring_boot_blog.springboot_blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_boot_blog.springboot_blog.dto.CategoryResponse;
import com.spring_boot_blog.springboot_blog.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
  
//	List<CategoryResponse> findByName(String name);
}
