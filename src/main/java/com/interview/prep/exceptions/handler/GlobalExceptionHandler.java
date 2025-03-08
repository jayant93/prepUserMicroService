package com.interview.prep.exceptions.handler;

import com.interview.prep.exceptions.UpdateOperationFailedException;
import com.interview.prep.exceptions.UserCreationFailedException;
import com.interview.prep.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserCreationFailedException.class)
    public ResponseEntity<String> handleUserCreationFailedException(UserCreationFailedException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(UpdateOperationFailedException.class)
    public ResponseEntity<String> handleUserUpdateFailedException(UpdateOperationFailedException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong "+ e.getMessage());
    }

}
