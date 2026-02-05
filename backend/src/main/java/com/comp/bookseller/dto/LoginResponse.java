package com.comp.bookseller.dto;

public class LoginResponse {
	private String loginMessage;
	private String token;
	public String getMessage() {
		return loginMessage;
	}
	public void setMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LoginResponse(String message, String token) {
		super();
		this.loginMessage = message;
		this.token = token;
	}
	
	
	
}
