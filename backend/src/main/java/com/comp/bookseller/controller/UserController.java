package com.comp.bookseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp.bookseller.dto.LoginRequest;
import com.comp.bookseller.dto.LoginResponse;
import com.comp.bookseller.dto.RegisterRequest;
import com.comp.bookseller.dto.RegisterResponse;
import com.comp.bookseller.service.UserService;
import com.comp.bookseller.util.JwtUtil;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest ) {
		RegisterResponse response  = service.registerUser(registerRequest);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = service.loginUser(loginRequest);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(loginResponse);
	}	
}
