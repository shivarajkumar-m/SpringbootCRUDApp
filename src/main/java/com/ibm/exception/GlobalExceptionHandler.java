package com.ibm.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	  @ExceptionHandler(StudentIdNotFound.class)
	    public ResponseEntity<ErrorResponse> handleStudentNotFound(
	            StudentIdNotFound ex,
	            HttpServletRequest request) {

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.NOT_FOUND.value(),
	                ex.getMessage(),
	                request.getRequestURI()
	        );

	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	  
	  @ExceptionHandler(BadRequestException.class)
	  public ResponseEntity<ErrorResponse> handlesBadRequest(BadRequestException e,HttpServletRequest request){
		  ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
		  return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	  }
	  
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<Map<String, Object>> handleValidationException(
	          MethodArgumentNotValidException ex,
	          HttpServletRequest request) {

	      Map<String, String> errors = new HashMap<>();

	      ex.getBindingResult().getFieldErrors().forEach(error -> {
	          errors.put(error.getField(), error.getDefaultMessage());
	      });

	      Map<String, Object> response = new HashMap<>();
	      response.put("time stamp", LocalDateTime.now());
	      response.put("message", "Validation Errror");
	      response.put("status", 400);
	      response.put("errors", errors);
	      response.put("path", request.getRequestURI());

	      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	  }
	  
	  
}


