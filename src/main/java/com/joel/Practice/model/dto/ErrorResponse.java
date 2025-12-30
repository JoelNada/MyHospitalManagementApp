package com.joel.Practice.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private LocalDateTime timeStamp;
    public ErrorResponse(HttpStatus code, String message){
        this.status=code;
        this.message=message;
        this.timeStamp=LocalDateTime.now();
    }
}
