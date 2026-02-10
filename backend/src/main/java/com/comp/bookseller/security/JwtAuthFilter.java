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
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
			String authHeader = request.getHeader("Authorization");
			
			String token = null;
			String email = null;
			
			if(authHeader!=null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				try {
				jwtUtil.validateToken(token);
			 	email = jwtUtil.extractEmail(token);
			 	
			 	if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,null,null);
			 			SecurityContextHolder.getContext().setAuthentication(auth);
			 		}
			 	}catch(Exception e) {
			}
				
			}
			filterChain.doFilter(request, response);
	}

}
