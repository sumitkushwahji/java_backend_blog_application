package com.sumit.api.javablogapis.services;

import java.util.List;

import com.sumit.api.javablogapis.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer UserId);
	
}
