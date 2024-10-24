package org.example;

import jakarta.persistence.EntityManager;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.dto.StudentSignUpDto;
import org.example.enumiration.City;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfLoan;
import org.example.enumiration.TypeOfMajor;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;
import org.example.util.HibernateUtil;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StudentService studentService = new StudentServiceImp();
        StudentSignUpDto studentSignUpDto = new StudentSignUpDto("Mohammad","Barari","Zahra",
                "Ali","123","980126378", LocalDate.parse("1380-04-25") ,
                "980126378","tahghighat",2023, TypeOfMajor.KRDANI, TypeOfCollege.ROOZANE, City.TEHRAN);
        try{
          Student student =  studentService.register(studentSignUpDto);
            LoanDto loanDto = new LoanDto("6037697577788133","025",LocalDate.parse("2022-04-03"),
                    TypeOfLoan.STUDENTLOAN);
          studentService.getLoan(student,loanDto);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
