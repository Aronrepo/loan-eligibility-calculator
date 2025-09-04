// src/App.tsx
import { useState } from "react";
import {
  Container,
  Paper,
  TextField,
  Button,
  Typography,
  Box,
  Alert,
} from "@mui/material";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { calculateMaxLoan } from "./api/loanApi";
import type { LoanRequest, LoanResponse } from "./types/loan";

/* ---------- validation ---------- */
const schema = yup.object({
  applicantName: yup.string().required("Name is required"),
  annualIncome: yup.number().min(20000).required("Annual income is required"),
  currentDebt: yup.number().min(0).required("Current debt is required"),
});

/* ---------- component ---------- */
function App() {
  const [result, setResult] = useState<LoanResponse | null>(null);
  const [error, setError] = useState<string>("");

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    watch,
  } = useForm<LoanRequest>({
    resolver: yupResolver(schema),
    defaultValues: {
      applicantName: "",
      annualIncome: 0,
      currentDebt: 0,
    },
  });

  const annualIncome = watch("annualIncome");
  const currentDebt   = watch("currentDebt");

  const onSubmit = (data: LoanRequest) => {
    setError("");
    setResult(null);
    calculateMaxLoan(data)
      .then(setResult)
      .catch((e: Error) => setError(e.message));
  };

  /* helper – clamp empty string to 0 instantly */
  const sanitizeNumeric =
    (name: "annualIncome" | "currentDebt") =>
    (e: React.ChangeEvent<HTMLInputElement>) => {
      const v = e.target.value;
      setValue(name, v === "" ? 0 : Number(v), {
        shouldValidate: true,
        shouldDirty: true,
      });
    };

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Paper elevation={3} sx={{ p: 4 }}>
        <Typography variant="h4" align="center" gutterBottom>
          Loan Calculator
        </Typography>

        <Box component="form" onSubmit={handleSubmit(onSubmit)} noValidate>
          <TextField
            fullWidth
            margin="normal"
            label="Applicant name"
            {...register("applicantName")}
            error={!!errors.applicantName}
            helperText={errors.applicantName?.message}
          />

          <TextField
            fullWidth
            margin="normal"
            label="Annual income (€)"
            type="number"
            value={annualIncome}
            onChange={sanitizeNumeric("annualIncome")}
            error={!!errors.annualIncome}
            helperText={errors.annualIncome?.message}
          />

          <TextField
            fullWidth
            margin="normal"
            label="Current debt (€)"
            type="number"
            value={currentDebt}
            onChange={sanitizeNumeric("currentDebt")}
            error={!!errors.currentDebt}
            helperText={errors.currentDebt?.message}
          />

          <Button type="submit" variant="contained" fullWidth sx={{ mt: 2 }}>
            Calculate
          </Button>
        </Box>

        {error && (
          <Alert severity="error" sx={{ mt: 2 }}>
            {error}
          </Alert>
        )}

        {result && (
          <Alert severity="success" sx={{ mt: 2 }}>
            <Typography variant="h6">Result</Typography>
            <Typography>Hello {result.applicantName},</Typography>
            <Typography>
              Maximum loan:{" "}
              <strong>€{result.maxLoanAmount.toLocaleString()}</strong>
            </Typography>
          </Alert>
        )}
      </Paper>
    </Container>
  );
}

export default App;