package com.sumit.api.javablogapis.services;

import java.util.List;

import com.sumit.api.javablogapis.entities.Post;
import com.sumit.api.javablogapis.payloads.PostDto;

public interface PostService {

	
//	Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryid);
	
//	update
	PostDto updatePost(PostDto postDto, Integer postId);
	
//	delete
	void deletePost(Integer postId);
	
//	get
	PostDto getPostById( Integer postId);
	
//	getall
	List<PostDto> getAllPost();
	
//	get all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
//	get all posts by user
	List<PostDto> getPostByUser(Integer userId);

//	search post
	List<Post> searchPost(String keyword);
}
