package com.comp.bookseller.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.comp.bookseller.config.SecurityBeans;
import com.comp.bookseller.dto.User;
import com.comp.bookseller.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired 
	private UserRepo repo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncode;

	public String registerUser(User user) {
		User dbUser = repo.getUserByEmail(user.getEmail());
		if(dbUser!=null) {
			return "Email Already Exists";
		}
		user.setRole("USER");
		
		String encodedPassword = passwordEncode.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		return "Registration successfull";
	}

	public User loginUser(String email, String password) {
		User dbUser = repo.getUserByEmail(email);
		if(dbUser!=null && passwordEncode.matches(password, dbUser.getPassword())) {
			System.out.println("User from DB: " + dbUser);
			return dbUser;
		}
		return null;
	}

}
