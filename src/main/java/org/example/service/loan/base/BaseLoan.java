package org.example.service.loan.base;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;

import java.util.List;

public interface BaseLoan {
    List<Loan> findLoan(Student student);
    Boolean checkCartOfStudent(LoanDto loan , List<Loan> loans);
    List<Bill> allNonPaidBillForALoan(Integer loanId);
    List<Bill> allPaidBillForALoan(Integer loanId);
    void saveLoan(Loan loan);
}
