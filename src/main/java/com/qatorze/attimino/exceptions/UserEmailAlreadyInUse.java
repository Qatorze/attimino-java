package com.qatorze.attimino.exceptions;

public class UserEmailAlreadyInUse extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserEmailAlreadyInUse() {
		super("Email already in use.");
	}
}