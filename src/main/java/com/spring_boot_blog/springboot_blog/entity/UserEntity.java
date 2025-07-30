package com.spring_boot_blog.springboot_blog.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="User_information")
public class UserEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String address;
	private String gender;
	private Integer age;
	@Column(length=10)
	private Long mobileNo;
	private String about;
	@JsonFormat(shape = Shape.STRING,pattern = "dd-mm-yyyy")
	private Date dob;
	@CreationTimestamp
	private Date createdDate;
	@UpdateTimestamp
	private Date updatedDate;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	List<PostEntity> post;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	List<CommentEntity> comment;
}
