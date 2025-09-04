package com.mycompany.loancalculator.exception.validation;

import com.mycompany.loancalculator.exception.LoanServiceBadRequestException;

public class DebtIncomeRatioException extends LoanServiceBadRequestException {
    public DebtIncomeRatioException(String message) {
        super(message);
    }
}
