package com.example.pharmacy.exception;

public class NoPharmacyFoundException extends RuntimeException {
	private String message;

	public NoPharmacyFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}
