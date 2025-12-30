package com.joel.Practice.exceptions.customExceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String s) {
        super(s);
    }
}
