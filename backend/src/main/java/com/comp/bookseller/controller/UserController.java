package com.comp.bookseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp.bookseller.dto.LoginRequest;
import com.comp.bookseller.dto.LoginResponse;
import com.comp.bookseller.dto.RegisterRequest;
import com.comp.bookseller.dto.RegisterResponse;
import com.comp.bookseller.entity.User;
import com.comp.bookseller.service.UserService;
import com.comp.bookseller.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest ) {
		RegisterResponse response  = service.registerUser(registerRequest);
		if(response.isRegisterSuccess()) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(response);
		}
		else {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(response);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = service.loginUser(loginRequest);
		if(loginResponse.getToken()!=null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(loginResponse);
		}
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(loginResponse);
	}
	
	
}
