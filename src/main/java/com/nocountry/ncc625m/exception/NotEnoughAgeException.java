package com.nocountry.ncc625m.exception;

public class NotEnoughAgeException extends RuntimeException{

    public NotEnoughAgeException() {
        super(String.format("You must be older than 18 in order to create a MyWallet account."));
    }
}
