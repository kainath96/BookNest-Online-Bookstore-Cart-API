package com.comp.bookseller.dto;

public class RegisterResponse {
	private String registerMessage;

	public RegisterResponse(String registerMessage) {
		super();
		this.registerMessage = registerMessage;
	}

	public String getRegisterMessage() {
		return registerMessage;
	}

	public void setRegisterMessage(String registerMessage) {
		this.registerMessage = registerMessage;
	}
	
	public RegisterResponse() {
		// TODO Auto-generated constructor stub
	}
}
