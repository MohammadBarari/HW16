package org.example.service.loan.housingLoan.imp;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.enumiration.City;
import org.example.enumiration.TypeOfLoan;
import org.example.service.loan.base.BaseLoanImp;
import org.example.service.loan.housingLoan.HousingLoanService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HousingLoanServiceImp extends BaseLoanImp implements HousingLoanService {

    @Override
    public void getHousingLoan(Student student, LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setTypeOfLoan(TypeOfLoan.HOUSING);
        loan.setStudent(student);
        loan.setCv22(loanDto.cvv2());
        loan.setCartExpiryDate(loanDto.expiresDate());
        loan.setCartNumber(loanDto.cartNumber());
        loan.setDateOfGet(LocalDateTime.now());
        loan.setBill(billService.billsCalculator(calculateMoneyForStudentHousingLoan(student), LocalDate.now()));
        saveLoan(loan);
        sendMoneyToStudent(student,calculateMoneyForStudentHousingLoan(student));
    }

    @Override
    public void sendMoneyToStudent(Student student, Long amount) {
    }

    @Override
    public Long calculateMoneyForStudentHousingLoan(Student student) {
        if (student.getCity() == City.TEHRAN)
            return 32_000_000L;
        if (
                student.getCity() == City.GILAN ||
                        student.getCity() == City.ISFAHAN ||
                        student.getCity() == City.EAST_AZERBAIJAN ||
                        student.getCity() == City.SHIRAZ ||
                        student.getCity() == City.KHUZESTAN ||
                        student.getCity() == City.QOM ||
                        student.getCity() == City.RAZAVI_KHORASAN ||
                        student.getCity() == City.KARAJ
        ){
            return 26_000_000L;
        }
        else {
            return 19_500_000L;
        }
    }
}
