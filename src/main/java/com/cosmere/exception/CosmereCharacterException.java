package com.cosmere.exception;

public class CosmereCharacterException extends Exception {
	
	public CosmereCharacterException(String message) {
		super(message);
	}
	
	public CosmereCharacterException(Throwable cause) {
		super(cause);
	}
	
	public CosmereCharacterException(String message,Throwable cause) {
		super(message, cause);
	}
	
}
