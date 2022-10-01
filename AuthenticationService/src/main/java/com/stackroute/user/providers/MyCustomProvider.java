package com.stackroute.user.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.stackroute.user.service.MyUserDetailsService;
import com.stackroute.user.service.UserAuthServiceImpl;

@Component
public class MyCustomProvider implements AuthenticationProvider  {
	
	@Autowired
	 private MyUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	 public Authentication authenticate(Authentication authentication) throws AuthenticationException {	  
	  String providedUsername = authentication.getPrincipal().toString();
	  UserDetails user = userDetailsService.loadUserByUsername(providedUsername);
	   
	  String providedPassword = authentication.getCredentials().toString();
	  String correctPassword = user.getPassword();
	  

	  if(!passwordEncoder.matches(providedPassword, correctPassword))
	   throw new RuntimeException("Incorrect Credentials");

	  	  
	  Authentication authenticationResult = 
	    new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
	  
	  SecurityContext context = SecurityContextHolder.getContext();
	  context.setAuthentication(authenticationResult);
	  return authenticationResult;
	 }
	
	
	@Override
	 public boolean supports(Class<?> authentication) {
	  return authentication.equals(UsernamePasswordAuthenticationToken.class);
	 }
	}