package com.ywq.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ywq.common.exception.BadRequestException;
import com.ywq.common.exception.ConflictException;
import com.ywq.common.exception.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHander {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHander.class);

 
	/**
	 * 404错误异常的捕获
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={BadRequestException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ResponseEntity badRequestNotFound(BadRequestException e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new ResponseEntity(HttpStatus.NOT_FOUND.value(), e.getErrorInfo());
	}

	/**
	 * 409错误异常的捕获
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={ConflictException.class})
	@ResponseStatus(value=HttpStatus.CONFLICT)
	public ResponseEntity conflictException(ConflictException e){
		logger.error("occurs error when execute method ,message {}",e.getMessage());
		return new ResponseEntity(HttpStatus.CONFLICT.value(), e.getErrorInfo());
	}

}
