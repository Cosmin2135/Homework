package com.example.petdemo.exception;

public class DataNotFoundException extends  RuntimeException {

    public DataNotFoundException(String message){
        super(message);
    }
}
