package com.comp.bookseller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id @GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String role;
	
	public User() {
	}
	
	public User(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public long getId() {
		return id;
	}
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
	
}
