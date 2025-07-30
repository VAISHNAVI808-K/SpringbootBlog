package com.spring_boot_blog.springboot_blog.exception;

public class ObjectShouldNotBeNull extends RuntimeException {

	public ObjectShouldNotBeNull(String msg) {
		super(msg);
	}
}
