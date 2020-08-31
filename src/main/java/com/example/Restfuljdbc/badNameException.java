package com.example.Restfuljdbc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class badNameException extends Exception{
    public badNameException (){

    }
    public badNameException (String message){
        super(message);
    }
}