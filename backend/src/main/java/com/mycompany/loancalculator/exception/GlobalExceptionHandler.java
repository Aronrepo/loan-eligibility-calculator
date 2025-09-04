package com.mycompany.loancalculator.exception;

import com.mycompany.loancalculator.dto.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanServiceException.class)
    public ResponseEntity<ApiResponseDto> handleLoanServiceException(
            LoanServiceException ex, HttpServletRequest request) {
        return buildErrorResponse(request, ex, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());

        }

        ApiResponseDto response = ApiResponseDto.error("Validation failed", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiResponseDto> buildErrorResponse(
            HttpServletRequest request, Exception ex, HttpStatus status) {
        String path = request.getRequestURI();
        ApiResponseDto response = ApiResponseDto.error(ex.getMessage());
        return ResponseEntity.status(status).body(response);
    }
}
