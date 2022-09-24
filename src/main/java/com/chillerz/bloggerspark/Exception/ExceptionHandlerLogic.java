package com.chillerz.bloggerspark.Exception;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerLogic extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity exceptionHandling(UserNotFoundException e, WebRequest request){

        if(e instanceof UserNotFoundException)
        {
             return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
