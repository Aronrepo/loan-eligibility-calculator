export interface LoanRequest {
  applicantName: string;
  annualIncome:    number;
  currentDebt:     number;
}

export interface LoanResponse {
  applicantName: string;
  annualIncome:    number;
  currentDebt:     number;
  maxLoanAmount:   number;
}