package com.example.pharmacy.exception;

public class InvalidDataException extends RuntimeException{
	String message;

	public InvalidDataException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
