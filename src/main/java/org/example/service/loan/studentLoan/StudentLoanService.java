package org.example.service.loan.studentLoan;

import org.example.domain.Student;
import org.example.dto.LoanDto;

public interface StudentLoanService {
    void getStudentLoan(Student student, LoanDto loanDto);
    void sendMoneyToStudent(Student student , Long amount);
    Long calculateMoneyStudentLoan(Student student);
}
