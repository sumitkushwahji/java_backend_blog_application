package com.sumit.api.javablogapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.api.javablogapis.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
