package com.sumit.api.javablogapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.api.javablogapis.entities.User;

//To do operation related to user entity database 
public interface UserRepo extends JpaRepository<User, Integer> {

}
