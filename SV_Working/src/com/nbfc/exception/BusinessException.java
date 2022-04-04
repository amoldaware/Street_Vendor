package com.nbfc.exception;

public class BusinessException extends Throwable {

	private static final long serialVersionUID = 1L;

	public BusinessException(Exception e) {
		super(e);
	}

	/**
	 * @param string
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Exception cause) {
		super(msg, cause);
	}

}
