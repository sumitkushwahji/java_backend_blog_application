package com.sumit.api.javablogapis.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	String resouceName;
	String fieldName;
	long field;
	public ResourceNotFoundException(String resouceName, String fieldName, long field) {
		super( String.format("%s not found with %s :  %s",resouceName,fieldName,field));
		resouceName = resouceName;
		fieldName = fieldName;
		field = field;
	}
}
