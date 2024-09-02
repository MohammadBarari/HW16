package org.example.service.loan;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.service.Bill.BillService;
import org.example.service.Bill.Imp.BillServiceImp;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.util.List;

public class BaseLoanImp implements BaseLoan{
    public StudentService studentService = new StudentServiceImp();
    public BillService billService = new BillServiceImp();
    @Override
    public List<Loan> findLoan(Student student) {
        return null;
    }


}
