package org.example.repository.loan.base;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;

import java.util.List;

public interface BaseLoanRepository {
    List<Loan> selectAllLoan(Student student);
    List<Bill> allNonPaidBillForALoan(Integer loanId);
    List<Bill> allPaidBillForALoan(Integer loanId);
    void saveLoan(Loan loan);
}
