package com.producer.api.Exception;

public class UnAuthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}
}
