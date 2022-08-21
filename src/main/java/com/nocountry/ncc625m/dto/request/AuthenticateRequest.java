package com.nocountry.ncc625m.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthenticateRequest {

	@NotBlank(message = "the email can't be null")
	@Email(message = "enter a correct email")
	private String email;

	@NotEmpty(message = "the password can't be null")
	private String password;
}