package com.nocountry.ncc625m.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {

    private Long id;

    private String firstName;

    private String lastName;

    @NotBlank(message = "the email can't be null")
    @Email(message = "enter a correct email")
    private String email;

    @NotBlank(message = "the password can't be null")
    private String password;

    private String photo;

}
