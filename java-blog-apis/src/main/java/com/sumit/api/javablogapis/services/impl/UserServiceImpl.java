package com.sumit.api.javablogapis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.api.javablogapis.payloads.UserDto;
import com.sumit.api.javablogapis.services.UserService;
import com.sumit.api.javablogapis.entities.*;
import com.sumit.api.javablogapis.payloads.*;
import com.sumit.api.javablogapis.repositories.*;
import com.sumit.api.javablogapis.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		User updatedUser = this.userRepo.save(user);
		UserDto userdto1 = this.userToUserDto(updatedUser);
		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream()
		.map(user -> this.userToUserDto(user))  //fetching data
		.collect(Collectors.toList());  //Collecting as List
		return userDtos;
	}

	@Override
	public void deleteUser(Integer UserId) {
		// TODO Auto-generated method stub
		User user =		this.userRepo.findById(UserId).orElseThrow(()->new ResourceNotFoundException("user", "Id", UserId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}

	private UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
