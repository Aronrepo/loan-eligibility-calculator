package com.mycompany.loancalculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String applicantName;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal annualIncome;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal currentDebt;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal maxLoanAmount;

    private LocalDate applicationDate = LocalDate.now();


}
