package com.example.pharmacy.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.exception.NoPharmacyFoundException;

import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ErrorStructure;

@RestController
public class PharmacyExceptionHandler {

	private final AppResponseBuilder appResponseBuilder;

	public PharmacyExceptionHandler(AppResponseBuilder appResponseBuilder) {
		super();
		this.appResponseBuilder = appResponseBuilder;
	}
	
	@ExceptionHandler(NoPharmacyFoundException .class)
	public static ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(NoPharmacyFoundException ex) {
		return AppResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(),"Pharmacy not found by Id");

	}
}
