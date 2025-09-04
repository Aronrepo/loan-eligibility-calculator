package com.mycompany.loancalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponseDto {
    private boolean success;
    private String message;
    private Object data;

    public static ApiResponseDto success(String message) {
        return new ApiResponseDto(true, message, null);
    }

    public static ApiResponseDto success(String message, Object data) {
        return new ApiResponseDto(true, message, data);
    }

    public static ApiResponseDto error(String message) {
        return new ApiResponseDto(false, message, null);
    }

    public static ApiResponseDto error(String message, Object data) {
        return new ApiResponseDto(false, message, data);
    }
}
