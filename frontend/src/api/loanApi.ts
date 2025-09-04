import type { LoanRequest, LoanResponse } from "../types/loan";

const BASE = '/api/v1';

export async function calculateMaxLoan(payload: LoanRequest): Promise<LoanResponse> {
  const res = await fetch(`${BASE}/loan/max`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    const err = await res.json().catch(() => ({ message: 'Network error' }));
    throw new Error(err.message || 'Unknown error');
  }
  return res.json();
}