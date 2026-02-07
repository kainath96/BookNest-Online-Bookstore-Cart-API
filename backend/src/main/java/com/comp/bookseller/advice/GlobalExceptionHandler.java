package com.comp.bookseller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.comp.bookseller.dto.LoginResponse;
import com.comp.bookseller.dto.RegisterResponse;
import com.comp.bookseller.exception.EmailAlreadyExistException;
import com.comp.bookseller.exception.InvalidLoginCredentialsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<RegisterResponse> handleEmailExist(EmailAlreadyExistException exception){
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new RegisterResponse(exception.getMessage(),false));
	}
	
	@ExceptionHandler(InvalidLoginCredentialsException.class)
	public ResponseEntity<LoginResponse> handInvalidLogin(InvalidLoginCredentialsException exception){
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new LoginResponse(exception.getMessage(),null));
	}
}
