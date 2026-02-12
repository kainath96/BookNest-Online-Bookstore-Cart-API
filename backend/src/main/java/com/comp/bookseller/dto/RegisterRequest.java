package com.comp.bookseller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	
	@NotBlank (message="Email is required") 
	@Email (message = "Invalid Email format") 
	private String email;
	
	@NotBlank (message="Password is Required") 
	@Size(min=6, message="Minimum 6 characters required")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RegisterRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public RegisterRequest() {
		// TODO Auto-generated constructor stub
	}
}
