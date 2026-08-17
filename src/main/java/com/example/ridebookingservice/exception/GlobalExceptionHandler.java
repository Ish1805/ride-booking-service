package com.example.ridebookingservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
		
		return new ErrorResponse (
			LocalDateTime.now(),
			HttpStatus.BAD_REQUEST.value(),
			ex.getMessage(),
			request.getRequestURI()
		);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
		return new ErrorResponse (
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(),
				request.getRequestURI()
		);
	}
	
}