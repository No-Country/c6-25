package com.nocountry.ncc625m.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class JwtBadRequestException extends RuntimeException {

	private HttpStatus httpStatus;
	private String message;

	public JwtBadRequestException(HttpStatus badRequest, String message) {
		super(String.format(message));
		this.setHttpStatus(badRequest);
		this.setMessage(message);
	}

}
