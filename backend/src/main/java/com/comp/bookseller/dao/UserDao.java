package com.comp.bookseller.dao;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.comp.bookseller.entity.User;
import com.comp.bookseller.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired 
	private UserRepo repo;

	public User registerUser(String email,String password) {
		User dbUser = repo.findUserByEmailAndPassword(email,password);
		if(dbUser==null) {
			User user = new User(email,password,"USER");
			repo.save(user);
			return user;
		}
		return null;
	}

	public User loginUser(String email, String password) {
		User dbUser = repo.findUserByEmailAndPassword(email, password);
		if(dbUser!=null){
			System.out.println("User from DB: " + dbUser);
			return dbUser;
		}
		return null;
	}

}
