package com.example.pharmacy.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.pharmacy.exception.AdminNotFoundByIdException;
import com.example.pharmacy.exception.InvalidDataException;
import com.example.pharmacy.exception.InvalidDateFormatException;
import com.example.pharmacy.exception.InvalidFileFormatException;
import com.example.pharmacy.exception.NoAdminFoundException;
import com.example.pharmacy.exception.NoMedicinesFoundException;
import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ErrorStructure;

import jakarta.validation.ConstraintViolationException;
@RestControllerAdvice
public class UserExceptionHandler {
	private final AppResponseBuilder appResponseBuilder;

	
  
	public UserExceptionHandler(AppResponseBuilder appResponseBuilder) {
		super();
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler (AdminNotFoundByIdException.class)
	public static ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(AdminNotFoundByIdException ex) {
		return AppResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(),"User not found by Id");
		
	}
	
	@ExceptionHandler(NoAdminFoundException.class)
	public static ResponseEntity<ErrorStructure<String>> handleNoUsersFound(NoAdminFoundException ex){
		return AppResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(),"User not found under requested criteria");
	}
	
	@ExceptionHandler(InvalidFileFormatException.class)
	public static <T> ResponseEntity<ErrorStructure<String>> handleInvalidFileFormat(InvalidFileFormatException ex) {
		return AppResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(),"File Format is Inavlid");
	}
	@ExceptionHandler(InvalidDataException.class) 
	public static <T> ResponseEntity<ErrorStructure<String>> handleInvalidData(InvalidDataException ex) {
		return AppResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(),"Data Is Invalid");
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public static <T> ResponseEntity<ErrorStructure<String>> handleInvalidDateFormat(InvalidDateFormatException ex) {
		return AppResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(),"Date Format is Invalid");
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public static <T> ResponseEntity<ErrorStructure<String>> handleConstraintViolationException(ConstraintViolationException ex) {
		return AppResponseBuilder.error(HttpStatus.BAD_REQUEST, ex.getMessage(),"Invalid Data Format");
	}
	
	@ExceptionHandler(NoMedicinesFoundException.class)
	public static <T> ResponseEntity<ErrorStructure<String>> handleNoMedicineFound(NoMedicinesFoundException ex) {
		return AppResponseBuilder.error(HttpStatus.BAD_REQUEST, ex.getMessage(),"No Medicine Found");
	}
	

}
