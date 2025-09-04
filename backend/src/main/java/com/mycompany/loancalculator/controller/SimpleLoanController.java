package com.mycompany.loancalculator.controller;

import com.mycompany.loancalculator.dto.LoanApplicationRequest;
import com.mycompany.loancalculator.dto.LoanApplicationResponse;
import com.mycompany.loancalculator.service.SimpleLoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
@Tag(name = "Loan", description = "Loan calculation endpoints")
public class SimpleLoanController {

    private final SimpleLoanService service;

    @PostMapping("/max")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Calculate maximum loan",
            description = "Returns the maximum loan amount a person can receive "
                    + "based on annual income and current debt.")
    public LoanApplicationResponse calculateMaxLoan(@Valid @RequestBody LoanApplicationRequest request) {
        BigDecimal max = service.maxLoan(request.annualIncome(), request.currentDebt());
        return new LoanApplicationResponse(
                request.applicantName(),
                request.annualIncome(),
                request.currentDebt(),
                max);
    }
}