package com.nocountry.ncc625m.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(){
        super(String.format("Invalid Credentials"));
    }
}
