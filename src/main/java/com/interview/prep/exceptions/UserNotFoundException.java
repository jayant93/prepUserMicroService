package com.interview.prep.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userDetail,Boolean isUserName){
        super(isUserName ?
                ("User Not Found with name : "+ userDetail )
                : ("User Not Found with id : "+userDetail )
            );
    }
}
