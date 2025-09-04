package com.mycompany.loancalculator.exception.validation;

import com.mycompany.loancalculator.exception.LoanServiceBadRequestException;
import com.mycompany.loancalculator.exception.LoanServiceException;

public class DebtIncomeRatioException extends LoanServiceBadRequestException {
    public DebtIncomeRatioException(String message) {
        super(message);
    }
}
