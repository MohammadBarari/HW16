package org.example.service.loan.housingLoan;

import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.service.loan.base.BaseLoan;

public interface HousingLoanService extends BaseLoan {
    void getHousingLoan(Student student , LoanDto loanDto);
    void sendMoneyToStudent(Student student , Long amount);
    Long calculateMoneyForStudentHousingLoan(Student student);
}
