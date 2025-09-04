# Loan Calculator – full-stack demo

Small Spring-Boot 3 + React + Vite application that tells a consumer how much money the bank is willing to lend based on annual income and existing debt.

## Business rule (backend)
* Debt-to-income ratio must be < 40 %  
* Max loan = (annual-income × 5) − (current-debt × 2)  
* All amounts are in EUR with two-decimal precision.

## Tech stack
Backend  
* Java 17  
* Spring-Boot 3 (web, validation, OpenAPI/Swagger)  
* Maven  

Frontend  
* React 18 + TypeScript  
* Vite  
* Material-UI + React-Hook-Form + Yup  

## Quick start (Docker-Compose – everything in one shot)
```bash
git clone git@github.com:Aronrepo/loan-eligibility-calculator.git
cd loan-eligibility-calculator
docker compose up
```
* Backend starts on http://localhost:8080
* Frontend starts on http://localhost:5173
* Swagger-UI: http://localhost:8080/swagger-ui.html

## Manual start (development)
Backend
```bash
cd backend
mvn spring-boot:run
```

Frontend (new terminal)
```bash
cd frontend
npm install
npm run dev
```

## Run tests
Backend
```bash
mvn test
```
Frontend
```bash
npm test
```

## API
`POST /api/v1/loan/max`  
Body (JSON)
```json
{
  "applicantName": "Jane Doe",
  "annualIncome": 60000,
  "currentDebt": 15000
}
```
Success 200
```json
{
  "applicantName": "Jane Doe",
  "annualIncome": 60000.00,
  "currentDebt": 15000.00,
  "maxLoanAmount": 270000.00
}
```
Error 400 – debt-to-income ≥ 40 % or validation failure  
Response shape: `{success:false, message:"…"}`

## Project layout
```
backend/src/main/java/…   Spring service & controller
backend/src/test/…        JUnit tests
frontend/src/…            React app
docker-compose.yml        Starts both sides
```
```