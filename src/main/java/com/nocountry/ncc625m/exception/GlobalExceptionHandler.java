package com.nocountry.ncc625m.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            AlreadyExistsException.class,
            BadCredentialsException.class
    })
    @ResponseBody
    public MessageResponse handleAlreadyExists (Exception e, HttpServletRequest request) {
        return new MessageResponse(LocalDateTime.now(), e, request);
    }

}
