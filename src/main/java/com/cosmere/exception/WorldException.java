package com.cosmere.exception;

public class WorldException extends Exception{
	
	public WorldException(String message) {
		super(message);
	}
	
	public WorldException(Throwable cause) {
		super(cause);
	}
	
	public WorldException(String message,Throwable cause) {
		super(message, cause);
	}
	
}
