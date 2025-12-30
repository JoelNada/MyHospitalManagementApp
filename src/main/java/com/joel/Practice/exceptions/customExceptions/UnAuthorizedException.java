package com.joel.Practice.exceptions.customExceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

public class UnAuthorizedException extends AuthenticationException {

    public UnAuthorizedException(@Nullable String msg) {
        super(msg);
    }
}
