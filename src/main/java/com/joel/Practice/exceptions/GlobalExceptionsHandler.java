package com.joel.Practice.exceptions;

import com.joel.Practice.exceptions.customExceptions.RoleNotFoundException;
import com.joel.Practice.exceptions.customExceptions.UserAlreadyExists;
import com.joel.Practice.model.dto.ErrorResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<@NonNull ErrorResponse> buildResponse(HttpStatus status, String message ) {
        ErrorResponse error = new ErrorResponse(status, message);
        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<@NonNull ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password!!");
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFound(RoleNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND,"Role Not Found !!");
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserAlreadyExists ex){
        return buildResponse(HttpStatus.CONFLICT,ex.getMessage());
    }
}
