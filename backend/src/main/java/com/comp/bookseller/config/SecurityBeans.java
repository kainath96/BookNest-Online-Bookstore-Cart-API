package com.comp.bookseller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//this annotation tells that this class contains important bean creation methods
@Configuration
public class SecurityBeans {

	//it is used for creating bean for libraries or any other external classes which i cannot access
	//@Compnent is also for bean creation but only for the classes which i have created or i can access and modify the code
	@Bean
	//here we are returning the bean(object) of BCryptPasswordEncoder to use in other classes for passwod hashing.
	//spring creates the bean once when the project it started & keeps the bean in its spring container.
	//when the user wants to use it they will directly autowire the bean object in that particular class.
	//for more clarity this class name and the variable name created during autowired is kept same 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
