package org.example.service.loan.tuitionFeeLoan;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.service.loan.BaseLoan;

public interface TuitionFeeLoan extends BaseLoan {
    void getTuitionFeeLoan(Student student , LoanDto loanDto);
    void sendMoneyToStudent(Student student , Long amount);
}
