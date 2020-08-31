package com.example.Restfuljdbc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoHeaderNameException extends Exception{
    public NoHeaderNameException(){

    }
    public NoHeaderNameException(String message){
        super(message);
    }
}