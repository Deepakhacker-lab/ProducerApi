package com.producer.api.Exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.producer.api.entity.StringResponse;


@ControllerAdvice
public class UserControllerAdvice {

	
	
	@ExceptionHandler(value= UserNotFoundException.class)
	public  ResponseEntity<StringResponse> handleException(UserNotFoundException exec){
		StringResponse add = new StringResponse("User not found");

		return new ResponseEntity<>(add, HttpStatus.NOT_FOUND);		
		
	}

	@ExceptionHandler
	public ResponseEntity<StringResponse> handleGlobalException(Exception exec){
		
		StringResponse add = new StringResponse(exec.getMessage());
		
		return new ResponseEntity<>(add, HttpStatus.BAD_REQUEST);		
		
	}
	
	@ExceptionHandler(value= UnAuthorizedException.class)
	public  ResponseEntity<StringResponse> handleException(UnAuthorizedException exec){
		StringResponse add = new StringResponse("Unauthorized");

		return new ResponseEntity<>(add, HttpStatus.UNAUTHORIZED);		
		
	}
	
	@ExceptionHandler(value= UserAlreadyExistException.class)
	public  ResponseEntity<String> handleException(UserAlreadyExistException exec){
		String add = exec.getMessage();

		return new ResponseEntity<>(add, HttpStatus.ALREADY_REPORTED);		
		
	}
	
	
}
