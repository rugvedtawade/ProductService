package com.scaler.productservice.Exceptions;

public class UserNotFoundException extends Exception // Custom Exception
{
    public UserNotFoundException(String message){
        super(message);
    }
}

/*
Types of exceptions

1. Checked - Compile time - extended directly from Exception class
2. unchecked - Run time - extended from RuntimeException class
 */
