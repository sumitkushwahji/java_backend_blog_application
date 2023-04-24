package com.sumit.api.javablogapis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.api.javablogapis.entities.Category;
import com.sumit.api.javablogapis.entities.Post;
import com.sumit.api.javablogapis.entities.User;
import com.sumit.api.javablogapis.exceptions.ResourceNotFoundException;
import com.sumit.api.javablogapis.payloads.PostDto;
import com.sumit.api.javablogapis.payloads.UserDto;
import com.sumit.api.javablogapis.repositories.CategoryRepo;
import com.sumit.api.javablogapis.repositories.PostRepo;
import com.sumit.api.javablogapis.repositories.UserRepo;
import com.sumit.api.javablogapis.services.PostService;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryid) {
	
		 User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user"," user_id", userId));
		 Category category = this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("category", "category_id", categoryid));
		 
		 Post  post = this.modelMapper.map(postDto, Post.class);
		 post.setImageName("default.png");
		 post.setAddedDate(new Date());

		 post.setUser(user);
		 post.setCategory(category);

		 Post newpost = this.postRepo.save(post);

		 
		 
		return this.modelMapper.map(newpost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPost() {

		List<Post> allposts = this.postRepo.findAll();

		List<PostDto> postDtos = allposts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));

		List<Post> posts= this.postRepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));

		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		
		
		return null;
	}

}
