package com.comp.bookseller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comp.bookseller.dto.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User getUserByEmail(String email);
	
	//@Query("select u from User u where u.email = ?1 and u.password = ?2")
	User findUserByEmailAndPassword(String email, String password);

}
