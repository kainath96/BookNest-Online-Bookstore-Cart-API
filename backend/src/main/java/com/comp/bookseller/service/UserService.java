package com.comp.bookseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp.bookseller.dao.UserDao;
import com.comp.bookseller.dto.User;
import com.comp.bookseller.util.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private JwtUtil jwtUtil;

	public String registerUser(User user) {
		return dao.registerUser(user);
	}

	public String loginUser(User user) {
		User dbUser = dao.loginUser(user.getEmail(),user.getPassword());
		if(dbUser!=null) {
			String loginToken = jwtUtil.generateToken(dbUser.getEmail());
			System.out.println("hiiiiiiiiiiii"+dbUser);
			return "{\"message\":\"Login successful\", \"token\":\"" + loginToken + "\"}";
        } else {
            return "{\"message\":\"Login failed\"}";
        
		}
	}

}
