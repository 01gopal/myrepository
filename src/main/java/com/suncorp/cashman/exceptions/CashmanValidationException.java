package com.suncorp.cashman.exceptions;

public class CashmanValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4909203621458273589L;

	public CashmanValidationException(String message) {
		super(message);
	}
	
	public CashmanValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
