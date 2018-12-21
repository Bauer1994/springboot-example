package com.ywq.common.exception;

public class ResponseEntity {
	private  Integer code;
	 
	private String msg;
 
 
	public ResponseEntity() {
		
	}

 
	public ResponseEntity(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}



	public Integer getCode() {
		return code;
	}
 
 
	public void setCode(Integer code) {
		this.code = code;
	}
 
 
	public String getMsg() {
		return msg;
	}
 
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
