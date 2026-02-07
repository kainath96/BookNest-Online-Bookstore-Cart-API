package com.comp.bookseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp.bookseller.dao.UserDao;
import com.comp.bookseller.dto.LoginRequest;
import com.comp.bookseller.dto.LoginResponse;
import com.comp.bookseller.dto.RegisterRequest;
import com.comp.bookseller.dto.RegisterResponse;
import com.comp.bookseller.entity.User;
import com.comp.bookseller.exception.EmailAlreadyExistException;
import com.comp.bookseller.exception.InvalidLoginCredentialsException;
import com.comp.bookseller.util.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private JwtUtil jwtUtil;

	public RegisterResponse registerUser(RegisterRequest registerRequest) {
		User dbResponse = dao.registerUser(registerRequest.getEmail(),registerRequest.getPassword());
		if(dbResponse!=null) {
			return new RegisterResponse("Registration successfull",true);
		}
		 throw new EmailAlreadyExistException("Email Already Exist");
	}

	public LoginResponse loginUser(LoginRequest loginRequest) {
		User dbUser = dao.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
		if(dbUser!=null) {
			String generatedToken = jwtUtil.generateToken(dbUser.getEmail());
			return new LoginResponse("Login Successfull",generatedToken);
		}else {
			throw new InvalidLoginCredentialsException("Invalid email or password");
		}
		
	}

}
