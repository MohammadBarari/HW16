package org.example.service.loan.tuitionFeeLoan.imp;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.enumiration.TypeOfLoan;
import org.example.enumiration.TypeOfMajor;
import org.example.repository.loan.TuitionFeeLoan.TuitionFeeLoanRepository;
import org.example.service.loan.BaseLoanImp;
import org.example.service.loan.tuitionFeeLoan.TuitionFeeLoan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TuitionFeeLoanImp extends BaseLoanImp implements TuitionFeeLoan {
    TuitionFeeLoanRepository tuitionFeeLoanRepository;
    @Override
    public void registerStudent() {

    }
    @Override
    public boolean validateStudent(Student student) {
        return false;
    }

    @Override
    public void getTuitionFeeLoan(Student student , LoanDto loanDto) {
        //todo : for example we send a loan to the student cart with its cvv2 and cartNumber and its cartExpiresDate and we save its cart information in loan Entity
        sendMoneyToStudent(student, calculateMoneyForStudentTuitionFeeLoan(student));
        //todo : send money so write for saving loan
        LocalDate dateOfGet = LocalDate.now();
        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setTypeOfLoan(TypeOfLoan.TUITION);
        loan.setBill(
                billsCalculator(calculateMoneyForStudentTuitionFeeLoan(student) , dateOfGet)
        );
        //todo : tutionLoanRepository.save(Loan loan);
    }

    @Override
    public void sendMoneyToStudent(Student student, Long amount) {

    }
    private Long calculateMoneyForStudentTuitionFeeLoan(Student student) {
        if (checkAllTypeOfStudentMajorType(student) ==1){
            return 1_000_000L;
        } else if (checkAllTypeOfStudentMajorType(student) ==2) {
            return 2_600_000L;
        } else if (checkAllTypeOfStudentMajorType(student) ==3) {
            return 65_000_000L;
        }
        return 0L;
    }

    private  Integer checkAllTypeOfStudentMajorType(Student student){
        if (student.getTypeOfMajor() == TypeOfMajor.KARSHENASIARSHADPEYVASTE
                ||student.getTypeOfMajor() == TypeOfMajor.KARSHENASIARSHADNAPEYVASTE
                ||student.getTypeOfMajor() == TypeOfMajor.KRDANI
        ){
            return 1;
        } else if (
                student.getTypeOfMajor() == TypeOfMajor.DOKTORAHERFEE
                        ||
                        student.getTypeOfMajor() == TypeOfMajor.DOKTORAPEYVASTE
                        ||
                        student.getTypeOfMajor() == TypeOfMajor.KARSHENASIARSHADPEYVASTE
        ) {
            return 2;
        } else if (
                student.getTypeOfMajor() == TypeOfMajor.DOKTORANAPEVASTE
        ) {
            return 3;
        }
        return 0;
    }

    private Set<Bill> billsCalculator(Long price , LocalDate dateOfGet){
        LocalDate timeOfGet = LocalDate.parse("2022-10-25");
        Long eachYear = price/5;
        Set<Bill> bills = new HashSet<>();
        List<Long> eachyears = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            eachyears.add(eachYear);
        }
        eachyears.add(eachYear + (price - eachYear * 5));
        //todo : it can goes into a method with long input and list<Long> output
        LocalDate localDateBill =timeOfGet;
        for (int i = 1; i <= 5; i++) {
            //todo: profit still has not been calculated
            Long eachMonth = eachyears.get(i)/12 * 2;
            for (int j = 0; j < 7; j++) {
                //it should be in method
                Bill bill = new Bill();
                bill.setAmount(eachMonth);
                localDateBill = localDateBill.plusDays(30);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
                //todo : should be in new method with List<bill> output and localdatestart and days input;
            }
            for (int j = 0; j < 4; j++) {
                Bill bill = new Bill();
                bill.setAmount(eachMonth);
                localDateBill = localDateBill.plusDays(31);
                bill.setExpiresDate(localDateBill);
                bill.setIsPaid(false);
                bills.add(bill);
            }
            {
                Bill bill = new Bill();
                bill.setAmount(eachMonth +(eachyears.get(i) - eachMonth*12));
                localDateBill = localDateBill.plusDays(31);
                bill.setExpiresDate(localDateBill);
                bills.add(bill);
            }
        }
        return bills;
    }
    //todo : its not for student tuitionLoan so have to be impelemented in other classes
    private Long calculateMoneyStudentLoan(Student student){
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
