package org.example.service.loan.tuitionFeeLoan.imp;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.repository.loan.TuitionFeeLoan.TuitionFeeLoanRepository;
import org.example.service.loan.tuitionFeeLoan.TuitionFeeLoan;

import java.util.function.Predicate;

public class TuitionFeeLoanImp implements TuitionFeeLoan {
    TuitionFeeLoanRepository tuitionFeeLoanRepository;
    @Override
    public void registerStudent() {

    }

    @Override
    public void validateStudent(Student student) {

    }

    @Override
    public void getTuitionLoan(Student student) {

    }

    @Override
    public Loan saveLoan(Loan loan) {
        tuitionFeeLoanRepository.save(loan);
        return loan;
    }

    public void getLoan(StudentLoanDto student){

    }
}
