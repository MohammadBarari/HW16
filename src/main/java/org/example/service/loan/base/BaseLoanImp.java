package org.example.service.loan.base;

import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.dto.LoanDtoForCheckCart;
import org.example.enumiration.TypeOfMajor;
import org.example.exeptions.CartDoesNotMatch;
import org.example.exeptions.PayTheBillError;
import org.example.repository.loan.base.BaseLoanRepository;
import org.example.repository.loan.base.imp.BaseLoanRepositoryImp;
import org.example.service.Bill.BillService;
import org.example.service.Bill.Imp.BillServiceImp;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BaseLoanImp implements BaseLoan{
    public BillService billService = new BillServiceImp();
    private BaseLoanRepository baseLoanRepository = new BaseLoanRepositoryImp();
    @Override
    public List<Loan> findLoan(Student student) {
        return baseLoanRepository.selectAllLoan(student);
    }

    @Override
    public Boolean checkCartOfStudent(LoanDtoForCheckCart loanDtoForCheckCart, List<Loan> loans) {
        if (loans != null){

            for (Loan lo : loans) {
                if (!(Objects.equals(lo.getCartNumber(), loanDtoForCheckCart.cartNumber())
                        && lo.getCartExpiryDate() == loanDtoForCheckCart.expiresDate()
                && lo.getCv22().equals(loanDtoForCheckCart.cvv2()))){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Bill> allNonPaidBillForALoan(Integer loanId) {
        return baseLoanRepository.allNonPaidBillForALoan(loanId);
    }

    @Override
    public List<Bill> allPaidBillForALoan(Integer loanId) {

        return baseLoanRepository.allPaidBillForALoan(loanId);
    }

    @Override
    public void saveLoan(Loan loan) {
        baseLoanRepository.saveLoan(loan);
    }

    @Override
    public Loan loanMapper(LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setDateOfGet(LocalDateTime.now());
        loan.setTypeOfLoan(loanDto.typeOfLoan());
        loan.setCv22(loanDto.cvv2());
        loan.setCartNumber(loanDto.cartNumber());
        loan.setCartExpiryDate(loanDto.expiresDate());
        return loan;
    }

    @Override
    public void PayTheBill(Integer billId, LoanDtoForCheckCart loanDtoForCheckCart,List<Loan> loans) throws PayTheBillError, CartDoesNotMatch {
        if (checkCartOfStudent(loanDtoForCheckCart,loans)){
            billService.payBill(billId);
        }
        else {
            throw new CartDoesNotMatch();
        }
    }

    public Integer checkAllTypeOfStudentMajorType(Student student){
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
}
