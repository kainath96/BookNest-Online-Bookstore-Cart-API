package com.comp.bookseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp.bookseller.dto.User;
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
	public String registerUser(@RequestBody User user) {
		return service.registerUser(user);
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		String userString = service.loginUser(user);
		System.out.println("hellloooooooooo"+userString);
		return userString;
	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestHeader("Authorization") String authHeader, HttpServletResponse response) {
	    String token = authHeader.replace("Bearer ", ""); // remove "Bearer " prefix
	    String result = jwtUtil.validateToken(token);
	    if (result.equals("Token expired")) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
	        return result;
	    } else if (result.equals("Invalid token")) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
	        return result;
	    }
	    return "Token is valid"+result;
	}
}
