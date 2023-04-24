package com.sumit.api.javablogapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.api.javablogapis.entities.Post;
import com.sumit.api.javablogapis.entities.User;
import com.sumit.api.javablogapis.entities.Category;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    
}
