package com.suncorp.cashman.exceptions;

public class CashmanExecutionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3267015218041698697L;
	
	public CashmanExecutionException(String message) {
		super(message);
	}
	
	public CashmanExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
