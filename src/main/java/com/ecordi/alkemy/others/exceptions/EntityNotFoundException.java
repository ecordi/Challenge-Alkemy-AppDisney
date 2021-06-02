package com.ecordi.alkemy.others.exceptions;

public class EntityNotFoundException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3465248351164499149L;

	public EntityNotFoundException() {
		super("Object or Id not found");
	}
	
	public EntityNotFoundException(String message) {
		super(message);
	}

}
