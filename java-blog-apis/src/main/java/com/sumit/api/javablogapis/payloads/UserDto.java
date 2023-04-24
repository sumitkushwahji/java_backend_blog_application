package com.sumit.api.javablogapis.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//This class is used to transfer data so that we will not expose our entity data to the //api or to the outside world
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "name should be of min 4 character")
	private String name;
	
	@Email(message = "type valid email")
	private String email;
	
	@NotEmpty
	@Size(max = 10,message = "max 10 chars allowed")
	private String password;
	
	@NotNull
	private String about;
}
