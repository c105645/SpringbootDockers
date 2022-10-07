package com.stackroute.newz.util.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class NewsControllerAdvice  {
	
	   @ExceptionHandler(NewsNotFoundExeption.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<Object> newsNotFoundHandler(NewsNotFoundExeption ex, WebRequest req) {
		 Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(NewsAlreadyExistsException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public ResponseEntity<Object>  newsAlreadyExistsHandler(NewsAlreadyExistsException ex, WebRequest req) {
	    	 Map<String, Object> body = new LinkedHashMap<>();
		        body.put("timestamp", LocalDateTime.now());
		        body.put("message", ex.getMessage());

		        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	    }
	    
	    @ExceptionHandler(NewsUnauthorizedException.class)
	    @ResponseStatus(HttpStatus.UNAUTHORIZED)
	    public ResponseEntity<Object>  newsUnauthorizedHandler(NewsUnauthorizedException ex, WebRequest req) {
	    	Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);	 
	        }
		    
		 
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest req) {
	    	Map<String, Object> errors = new LinkedHashMap<String, Object>();
	        errors.put("timestamp", LocalDateTime.now());
	        ex.getBindingResult().getAllErrors().forEach(error -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
	        });
	        
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);	 

	    }


}
