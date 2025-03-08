package com.interview.prep.exceptions;

public class UpdateOperationFailedException extends RuntimeException{
    public  UpdateOperationFailedException(String exceptionMessage){
        super(exceptionMessage);
    }
}
