package com.ecordi.alkemy.others.exceptions;


public class ObjectNameOrIdNotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147619710130574147L;

	public ObjectNameOrIdNotFound() {
		super("Object or Id not foun");
	}
	
	public ObjectNameOrIdNotFound(String message) {
		super(message);
	}
}