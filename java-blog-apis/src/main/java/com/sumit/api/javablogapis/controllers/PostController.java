package com.sumit.api.javablogapis.controllers;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.api.javablogapis.entities.Post;
import com.sumit.api.javablogapis.payloads.PostDto;
import com.sumit.api.javablogapis.services.FileService;
import com.sumit.api.javablogapis.services.PostService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
    @PathVariable Integer categoryId ){

        PostDto createpost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createpost, HttpStatus.CREATED);
    }


    // Get Post By user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){

        List<PostDto> posts = this.postService.getPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Get Post By category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){

        List<PostDto> posts = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Get all post

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(){

        List<PostDto> allPost = this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
    }

    // Get Post By id

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

        PostDto PostDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(PostDto, HttpStatus.OK);
    }


    // post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage( @RequestParam("image") MultipartFile image , @PathVariable Integer postId) throws IOException{

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);     
    }


    // method to serve file
    @GetMapping(value ="/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException{
            InputStream resource = this.fileService.getResources(path, imageName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource,response.getOutputStream());
    }
 }
