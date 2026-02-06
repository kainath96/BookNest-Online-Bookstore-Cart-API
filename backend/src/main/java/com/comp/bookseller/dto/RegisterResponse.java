package com.comp.bookseller.dto;

public class RegisterResponse {
	private String registerMessage;
	private boolean registerSuccess;
	
	public RegisterResponse() {
	}

	public RegisterResponse(String registerMessage, boolean registerSuccess) {
		super();
		this.registerMessage = registerMessage;
		this.registerSuccess = registerSuccess;
	}

	public String getRegisterMessage() {
		return registerMessage;
	}

	public void setRegisterMessage(String registerMessage) {
		this.registerMessage = registerMessage;
	}

	public boolean isRegisterSuccess() {
		return registerSuccess;
	}

	public void setRegisterSuccess(boolean registerSuccess) {
		this.registerSuccess = registerSuccess;
	}
	
	
}
