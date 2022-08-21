package com.nocountry.ncc625m.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterRequest {

	@NotBlank(message = "the name can't  be blank")
	private String firstName;
	
	@NotBlank(message = "the lastName can't  be blank")
	private String lastName;

	@NotNull(message = "age can0t be null")
	private Long age;

	@NotBlank(message = "the email can't  be blank")
	@Email(message = "enter a correct email")
	private String email;

	@NotBlank(message = "the password can't  be blank")
	private String password;
}