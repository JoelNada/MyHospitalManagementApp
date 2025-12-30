package com.joel.Practice.helper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class ApiLogFilter extends OncePerRequestFilter {
    @Value("${spring.application.name}")
    String appName;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        //Code starts here...


        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response);

        int status = response.getStatus();
        String color;

        if (status >= 200 && status < 300) {
            color = GREEN;
        } else if (status >= 400 && status < 500) {
            color = YELLOW;
        } else if (status >= 500) {
            color = RED;
        } else {
            color = RESET;
        }

        log.info("{}{} | {} {} -> {} ({} ms) | Remote-IP: {} | User-Agent: {} | Params: {}{}",
                color,
                appName,
                request.getMethod(),
                request.getRequestURI(),
                status,
                System.currentTimeMillis() - startTime,
                request.getRemoteAddr(),
                request.getHeader("User-Agent"),
                request.getQueryString() != null ? request.getQueryString() : "-",
                RESET
        );

    }
}
