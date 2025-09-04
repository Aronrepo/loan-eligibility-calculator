package com.mycompany.loancalculator.exception;

public class LoanServiceBadRequestException extends LoanServiceException{
    protected LoanServiceBadRequestException(String message) {
        super(message, org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
