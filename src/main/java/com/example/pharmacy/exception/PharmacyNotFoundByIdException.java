package com.example.pharmacy.exception;


@SuppressWarnings("serial")
public class PharmacyNotFoundByIdException {
	private String message;

	public PharmacyNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


	

}
