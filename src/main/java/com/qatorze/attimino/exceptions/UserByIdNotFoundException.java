package com.qatorze.attimino.exceptions;

public class UserByIdNotFoundException extends  RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserByIdNotFoundException(Long id) {
		super("User by id ''"+id+"'' not found.");
	}
}
