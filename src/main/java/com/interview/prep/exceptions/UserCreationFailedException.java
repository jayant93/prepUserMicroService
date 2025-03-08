package com.interview.prep.exceptions;

public class UserCreationFailedException extends RuntimeException{

    public UserCreationFailedException(String exceptionMessage){
        super(exceptionMessage);
    }
}
