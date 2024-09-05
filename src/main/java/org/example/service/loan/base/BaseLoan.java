package org.example.service.loan.base;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.dto.LoanDtoForCheckCart;
import org.example.exeptions.CartDoesNotMatch;
import org.example.exeptions.PayTheBillError;

import java.util.List;

public interface BaseLoan {
    List<Loan> findLoan(Student student);
    Boolean checkCartOfStudent(LoanDtoForCheckCart loanDtoForCheckCart , List<Loan> loans);
    List<Bill> allNonPaidBillForALoan(Integer loanId);
    List<Bill> allPaidBillForALoan(Integer loanId);
    void saveLoan(Loan loan);
    Loan loanMapper(LoanDto loanDto);
    void PayTheBill(Integer billId, LoanDtoForCheckCart loanDtoForCheckCart,List<Loan> loans) throws PayTheBillError, CartDoesNotMatch;
}
