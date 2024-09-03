package org.example.service.loan.base;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.service.Bill.BillService;
import org.example.service.Bill.Imp.BillServiceImp;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.util.List;
import java.util.Objects;

public class BaseLoanImp implements BaseLoan{
    public StudentService studentService = new StudentServiceImp();
    public BillService billService = new BillServiceImp();
    @Override
    public List<Loan> findLoan(Student student) {
        return null;
    }

    @Override
    public Boolean checkCartOfStudent(LoanDto loan , List<Loan> loans) {
        if (loans != null){
            for (Loan lo : loans) {
                if (!(Objects.equals(lo.getCartNumber(), loan.cartNumber())
                        && lo.getCartExpiryDate() == loan.expiresDate())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Bill> allNonPaidBillForALoan(Integer loanId) {
        return baseLoanRepository.findAllNonPaidBills(loanId);
    }

    @Override
    public List<Bill> allPaidBillForALoan(Integer loanId) {
        return baseLoanRepository.findAllPaidBills(loanId);
    }


}
