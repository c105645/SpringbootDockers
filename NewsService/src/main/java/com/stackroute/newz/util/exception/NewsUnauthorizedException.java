package com.stackroute.newz.util.exception;
import javax.servlet.ServletException;

import io.jsonwebtoken.io.IOException;



public class NewsUnauthorizedException extends  RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NewsUnauthorizedException(String message) {
		
		super(message);
		
	}

	

}

