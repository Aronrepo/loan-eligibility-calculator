package com.mycompany.loancalculator.exception;

import org.springframework.http.HttpStatus;

public class LoanServiceException extends RuntimeException{
    private final HttpStatus status;

    protected LoanServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
