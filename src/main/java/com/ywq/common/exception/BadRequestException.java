package com.ywq.common.exception;

public class BadRequestException extends BaseException {

	private String field;
	private String message;
	
	
	public BadRequestException() {
		
	}

	
	public BadRequestException(String field, String message) {
		super.setErrorInfo(field + " " + message);
		this.field = field;
		this.message = message;
	}


	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
