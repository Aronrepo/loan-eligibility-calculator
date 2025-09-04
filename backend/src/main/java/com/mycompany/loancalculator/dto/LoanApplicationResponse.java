package com.mycompany.loancalculator.dto;

import java.math.BigDecimal;

public record LoanApplicationResponse(
        String applicantName,
        BigDecimal annualIncome,
        BigDecimal currentDebt,
        BigDecimal maxLoanAmount
) {
}
