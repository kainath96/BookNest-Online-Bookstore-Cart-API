package com.comp.bookseller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comp.bookseller.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User getUserByEmail(String email);
	
	User findUserByEmailAndPassword(String email, String password);

}
