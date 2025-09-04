package com.mycompany.loancalculator.service;

import com.mycompany.loancalculator.exception.validation.DebtIncomeRatioException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimpleLoanService {
    private static final BigDecimal MIN_INCOME      = new BigDecimal("20000");
    private static final BigDecimal MAX_DTI_RATIO   = new BigDecimal("0.40");
    private static final BigDecimal INCOME_MULTIPLIER = new BigDecimal("5");
    private static final BigDecimal DEBT_MULTIPLIER   = new BigDecimal("2");

    public BigDecimal maxLoan(BigDecimal annualIncome, BigDecimal currentDebt) {

        BigDecimal dti = currentDebt.divide(annualIncome, 4, RoundingMode.HALF_UP);
        if (dti.compareTo(MAX_DTI_RATIO) >= 0)
            throw new DebtIncomeRatioException("Debt-to-income ratio must be below 40 %");

        BigDecimal raw = annualIncome.multiply(INCOME_MULTIPLIER)
                .subtract(currentDebt.multiply(DEBT_MULTIPLIER));

        return raw.max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }
}
