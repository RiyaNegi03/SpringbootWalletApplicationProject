package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(InvalidAccountException.class)
	
	public ResponseEntity<MyErrorDetails> myExpHandler(InvalidAccountException ie, WebRequest wr)  {

		
		MyErrorDetails err= new MyErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDescription(wr.getDescription(false));
		
		
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST );
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandler2(Exception ie,WebRequest wr)  {
//		System.out.println("inside myHandler2 method...");
		
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDescription(wr.getDescription(false));
		
		
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST );	
		
	
	}
	


	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
			
	
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
	
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
					
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me)  {
	
		
MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
			
	}
	
	
	
	
	// 1: application specific
	//2. generic exception handler
	// 3 . not found excetion 
	// 4. MethodArgumentNotValidException
	
	
	


}