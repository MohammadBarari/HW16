package org.example.repository.loan.base;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;

import java.util.List;

public interface BaseLoan {
    List<Loan> selectAllLoan(Student student);
    Boolean checkCartOfStudent(LoanDto loan , List<Loan> loans);
    List<Bill> allNonPaidBillForALoan(Integer loanId);
    List<Bill> allPaidBillForALoan(Integer loanId);
}
