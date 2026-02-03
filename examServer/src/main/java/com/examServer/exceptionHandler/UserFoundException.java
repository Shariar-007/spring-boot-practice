package com.examServer.exceptionHandler;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super(String.format("User with this Username is already there !! try with another different name."));
    }

    public UserFoundException(String msg) {
        super(msg);
    }
}
