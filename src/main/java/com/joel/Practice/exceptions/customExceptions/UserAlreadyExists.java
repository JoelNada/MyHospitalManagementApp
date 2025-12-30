package com.joel.Practice.exceptions.customExceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String s) {
        super(s);
    }
}
