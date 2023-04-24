package com.sumit.api.javablogapis.payloads;

import java.util.Date;

import com.sumit.api.javablogapis.entities.Category;
import com.sumit.api.javablogapis.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer id;
	
	private String title;
	
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
}
