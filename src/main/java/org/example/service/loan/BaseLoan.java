package org.example.service.loan;

import org.example.domain.Loan;
import org.example.domain.Student;

import java.util.List;

public interface BaseLoan {
    List<Loan> findLoan(Student student);
}
