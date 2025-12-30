package com.joel.Practice.exceptions.customExceptions;

import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.BadCredentialsException;

public class BadCredentials extends BadCredentialsException {
    public BadCredentials(@NonNull String msg) {
        super(msg);
    }
}
