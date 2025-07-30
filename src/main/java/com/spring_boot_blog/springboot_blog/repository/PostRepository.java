package com.spring_boot_blog.springboot_blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_blog.springboot_blog.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
