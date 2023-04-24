package com.sumit.api.javablogapis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaBlogApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaBlogApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelmapper() {
		  return new ModelMapper();
	}
}
