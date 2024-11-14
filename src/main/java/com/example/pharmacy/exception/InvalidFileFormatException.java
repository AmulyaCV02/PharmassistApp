package com.example.pharmacy.exception;

public class InvalidFileFormatException extends RuntimeException {
	String message;

	public InvalidFileFormatException(String message) 
	{
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}