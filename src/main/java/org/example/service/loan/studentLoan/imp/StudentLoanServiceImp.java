package org.example.service.loan.studentLoan.imp;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.enumiration.TypeOfLoan;
import org.example.service.loan.base.BaseLoanImp;
import org.example.service.loan.studentLoan.StudentLoanService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentLoanServiceImp extends BaseLoanImp implements StudentLoanService {
    @Override
    public void getStudentLoan(Student student, LoanDto loanDto) {
        //todo : for example we send a loan to the student cart with its cvv2 and cartNumber and its cartExpiresDate and we save its cart information in loan Entity
        sendMoneyToStudent(student, calculateMoneyStudentLoan(student));
        //todo : send money so write for saving loan
        LocalDate dateOfGet = LocalDate.now();
        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setTypeOfLoan(TypeOfLoan.STUDENTLOAN);
        loan.setBill(
                billService.billsCalculator(calculateMoneyStudentLoan(student) , dateOfGet)
        );
        loan.setCartNumber(loanDto.cartNumber());
        loan.setCv22(loan.getCv22());
        loan.setCartExpiryDate(loanDto.expiresDate());
        loan.setDateOfGet(LocalDateTime.now());
        saveLoan(loan);
        //todo : tutionLoanRepository.save(Loan loan);
    }

    @Override
    public void sendMoneyToStudent(Student student, Long amount) {

    }

    public Long calculateMoneyStudentLoan(Student student){
        if (checkAllTypeOfStudentMajorType(student) ==1){
            return 1_900_000L;
        } else if (checkAllTypeOfStudentMajorType(student) ==2) {
            return 2_250_000L;
        } else if (checkAllTypeOfStudentMajorType(student) ==3) {
            return 2_600_000L;
        }
        return 0L;
    }
}
