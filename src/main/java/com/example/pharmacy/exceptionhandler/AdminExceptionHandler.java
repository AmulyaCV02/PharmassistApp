package com.example.pharmacy.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.pharmacy.exception.AdminNotFoundByIdException;
import com.example.pharmacy.exception.NoAdminFoundException;
import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ErrorStructure;

public class AdminExceptionHandler {
	private final AppResponseBuilder appResponseBuilder;

	
  
	public AdminExceptionHandler(AppResponseBuilder appResponseBuilder) {
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


}
