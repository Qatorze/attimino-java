package com.qatorze.attimino.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qatorze.attimino.exceptions.InvalidCredentialsException;
import com.qatorze.attimino.exceptions.UserByIdNotFoundException;
import com.qatorze.attimino.exceptions.UserEmailAlreadyInUse;

public class GlobalExceptionHandler {

	/*
	 * Tipi di errori ritornati
	 * 
	 * NOT_FOUND -> 404
	 * UNAUTHORIZED -> 401
	 * 
	 */
	
	
	// Gestione delle eccezioni del modello Cliente
    
    @ExceptionHandler(UserByIdNotFoundException.class)
    public ResponseEntity<String> handleUserByIdNotFoundException(UserByIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    
    @ExceptionHandler(UserEmailAlreadyInUse.class)
    public ResponseEntity<String> handleClienteEmailAlreadyInUse(UserEmailAlreadyInUse e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
