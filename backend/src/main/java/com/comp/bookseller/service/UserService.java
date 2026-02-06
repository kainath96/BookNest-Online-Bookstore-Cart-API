package com.comp.bookseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.comp.bookseller.dao.UserDao;
import com.comp.bookseller.dto.LoginRequest;
import com.comp.bookseller.dto.LoginResponse;
import com.comp.bookseller.dto.RegisterRequest;
import com.comp.bookseller.dto.RegisterResponse;
import com.comp.bookseller.entity.User;
import com.comp.bookseller.util.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private JwtUtil jwtUtil;

	public ResponseEntity<RegisterResponse> registerUser(RegisterRequest registerRequest) {
		User dbResponse = dao.registerUser(
				registerRequest.getEmail(),
				registerRequest.getPassword());
		
		if(dbResponse!=null) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(new RegisterResponse("Registration Successfull"));
		}
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new RegisterResponse("Registration Failed:Email Already Exist"));
		
	}

	public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
		User dbUser = dao.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
		if(dbUser!=null) {
			String generatedToken = jwtUtil.generateToken(dbUser.getEmail());
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new LoginResponse("Login Successfull", generatedToken));
		}else {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new LoginResponse("Login Failed", null));
		}
		
	}

}
