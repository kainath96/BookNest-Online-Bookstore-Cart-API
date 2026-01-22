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
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
