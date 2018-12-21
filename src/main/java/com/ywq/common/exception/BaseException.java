package com.ywq.common.exception;

public class BaseException extends RuntimeException {

	private String errorInfo;

	public BaseException(String errorInfo) {
		super();
		this.errorInfo = errorInfo;
	}

	public BaseException() {
	
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}
