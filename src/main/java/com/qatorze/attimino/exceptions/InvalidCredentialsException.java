package com.qatorze.attimino.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public InvalidCredentialsException() {
        super("Credenziali non valide.");
    }
}