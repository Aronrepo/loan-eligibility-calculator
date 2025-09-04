package com.mycompany.loancalculator.service;

import com.mycompany.loancalculator.exception.validation.DebtIncomeRatioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleLoanServiceTest {

    private final SimpleLoanService service = new SimpleLoanService();

    @Test
    @DisplayName("Calculate max loan when DTI < 40 %")
    void whenDtiBelowThreshold_thenCalculateCorrectly() {
        // GIVEN an applicant with income 60 000 € and debt 15 000 € (DTI = 25 %)
        BigDecimal income = new BigDecimal("60000");
        BigDecimal debt   = new BigDecimal("15000");

        // WHEN the maximum loan is calculated
        BigDecimal actual = service.maxLoan(income, debt);

        // THEN the result is 270 000 €
        assertThat(actual).isEqualByComparingTo("270000.00");
    }

    @Test
    @DisplayName("Allow zero debt")
    void whenNoDebt_thenCalculateCorrectly() {
        // GIVEN an applicant with income 50 000 € and no debt
        BigDecimal income = new BigDecimal("50000");
        BigDecimal debt   = BigDecimal.ZERO;

        // WHEN the maximum loan is calculated
        BigDecimal actual = service.maxLoan(income, debt);

        // THEN the result is 250 000 €
        assertThat(actual).isEqualByComparingTo("250000.00");
    }

    @Test
    @DisplayName("Reject application when DTI = 40 %")
    void whenDtiExactlyAtThreshold_thenThrow() {
        // GIVEN an applicant with income 100 000 € and debt 40 000 € (DTI = 40 %)
        BigDecimal income = new BigDecimal("100000");
        BigDecimal debt   = new BigDecimal("40000");

        // WHEN the maximum loan is calculated
        // THEN a DebtIncomeRatioException is thrown
        assertThatThrownBy(() -> service.maxLoan(income, debt))
                .isInstanceOf(DebtIncomeRatioException.class)
                .hasMessageContaining("below 40 %");
    }

    @Test
    @DisplayName("Reject application when DTI > 40 %")
    void whenDtiAboveThreshold_thenThrow() {
        // GIVEN an applicant with income 100 000 € and debt 41 000 € (DTI = 41 %)
        BigDecimal income = new BigDecimal("100000");
        BigDecimal debt   = new BigDecimal("41000");

        // WHEN the maximum loan is calculated
        // THEN a DebtIncomeRatioException is thrown
        assertThatThrownBy(() -> service.maxLoan(income, debt))
                .isInstanceOf(DebtIncomeRatioException.class);
    }
}