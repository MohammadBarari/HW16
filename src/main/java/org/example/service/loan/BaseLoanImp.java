package org.example.service.loan;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.util.List;

public class BaseLoanImp implements BaseLoan{
    
    @Override
    public List<Loan> findLoan(Student student) {
        return null;
    }


}
