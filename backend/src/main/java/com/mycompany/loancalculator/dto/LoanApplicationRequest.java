package com.mycompany.loancalculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Schema(description = "Payload for calculating the maximum loan amount")
public record LoanApplicationRequest(
        @Schema(description = "Full name of the applicant", example = "Jane Doe")
        @NotBlank(message = "Applicant name is mandatory")
        String applicantName,

        @Schema(description = "Annual gross income (EUR)",
                example = "45000.00",
                minimum = "20000", exclusiveMinimum = false)
        @DecimalMin(value = "20000.00", inclusive = true,
                message = "Annual income must be ≥ 20 000")
        @Digits(integer = 12, fraction = 2,
                message = "Income must have ≤ 2 decimal places")
        @Positive
        BigDecimal annualIncome,

        @Schema(description = "Total current debt (EUR)",
                example = "5000.00",
                minimum = "0", exclusiveMinimum = false)
        @DecimalMin(value = "0.00", inclusive = true,
                message = "Debt cannot be negative")
        @Digits(integer = 12, fraction = 2,
                message = "Debt must have ≤ 2 decimal places")
        BigDecimal currentDebt
) {}