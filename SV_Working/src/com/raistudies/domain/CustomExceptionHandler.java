package com.raistudies.domain;


public class CustomExceptionHandler extends RuntimeException {

	
private String message;

	
	public String getMessage() {
	return message;
}


public void setMessage(String message) {
	this.message = message;
}


	public CustomExceptionHandler(String message) {
		//this.message = message;
		this.message=message;
	}

	
	
}
