package com.ibm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
public class ErrorResponse {
	private String timestamp;
	private int status;
	private String message;
	private String path;
	
	public ErrorResponse(int status,String message, String path) {
		this.timestamp=java.time.LocalDate.now().toString();
		this.status=status;
		this.message=message;
		this.path=path;
	}

}
