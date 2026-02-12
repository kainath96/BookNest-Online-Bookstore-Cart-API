package com.comp.bookseller.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException exception){
		Map<String,String> errors = new HashMap<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		for(FieldError e : fieldErrors) {
			String fieldName = e.getField();
			String errorMessage = e.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errors);
	}
	
	
}
