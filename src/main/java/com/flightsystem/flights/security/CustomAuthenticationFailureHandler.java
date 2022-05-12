package com.flightsystem.flights.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception instanceof BadCredentialsException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,401);
            response.sendError(401);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,500);
            response.sendError(500);
        }
    }
}