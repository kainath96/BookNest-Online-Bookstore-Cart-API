package com.comp.bookseller.util;


import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	private static final String SECRET = "qwertyuioplkjhgfdsazxcvbnm123456789987456321";
	
	private Key key;
	
	//this annotation is used for methods that needs to be run automatically after an obj is fullu created & dependencies add.
	//but runs before the application starts accepting the request.
	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
		 System.out.println("JWT key generated: " + key);
	}
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuer("BookSeller")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+600000))
				.signWith(key)
				.compact();
	}
	
	
	 public String validateToken(String token) {
	        try {
	            Jws<Claims> claims = Jwts.parserBuilder()
	                    .setSigningKey(key)
	                    .build()
	                    .parseClaimsJws(token);

	            return claims.getBody().getSubject(); // valid: return email
	        } catch (ExpiredJwtException e) {
	            return "Token expired";
	        } catch (JwtException e) {
	            return "Invalid token";
	        }
	    }
}
