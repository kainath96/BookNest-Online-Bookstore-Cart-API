package com.comp.bookseller.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.comp.bookseller.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//this extending class is abstract class. it contain different methods(concrete) but only below method is abstract method. So we need to implement this single method.
//OncePerRequestFilter already contains:doFilter() implementation logic to ensure filter runs once,request tracking code,error handling code
//but the doFilter does not know based on what logic filter should happen.
//so that logic is written in the doFilterInternal method and is called by doFilter method.
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//this will take the data present in the header called "Authorization"
		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		String email = null;
		
		//checks wheather the authHeader is not null & starts with "Bearer "'
		//if yes, set the remaining part after substringing the "Bearer " varlue to token
		//for extracting the username using a different method whose logiv is present in another class
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			email = jwtUtil.extractUserName(token);
		}
		
		//this is to check wheather the email i valid or not 
		//and the autherization is already given or not. for every request authorization will becom null afer the request is completed.
		//so here it is checking wheather the request is new or not
		//then checks wheather the token is valid or not.
		//if valid an object is created and email is passed to it.
		//that email will be set to authentication whill be stored in the SecurityContextHolder
		//stating that this is the valid user(email) for this particular request
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			if(jwtUtil.validateToken(token).equals("Valid")) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,null,null);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		//jwt has completed its part(authorization) now it should move to next filter(if any)/controller
		filterChain.doFilter(request, response); 
	}

	
	

}
