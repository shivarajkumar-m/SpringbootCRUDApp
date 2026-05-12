package com.ibm.exception;

import lombok.experimental.SuperBuilder;

public class BadRequestException extends RuntimeException{
	public BadRequestException(String message) {
		super(message);
	
}
}
