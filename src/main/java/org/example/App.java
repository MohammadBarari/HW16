package org.example;

import jakarta.persistence.EntityManager;
import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.*;
import org.example.enumiration.City;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfLoan;
import org.example.enumiration.TypeOfMajor;
import org.example.menu.MainMenu;
import org.example.service.Bill.BillService;
import org.example.service.Bill.Imp.BillServiceImp;
import org.example.service.loan.base.BaseLoan;
import org.example.service.loan.base.BaseLoanImp;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;
import org.example.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        StudentService studentService = new StudentServiceImp();
//        StudentSignUpDto studentSignUpDto = new StudentSignUpDto("Mohammad","Barari","Zahra",
//                "Ali","123","980126378", LocalDate.parse("1380-04-25") ,
//                "980126378","tahghighat",2023, TypeOfMajor.KRDANI, TypeOfCollege.AZAZ, City.TEHRAN);
//        try{
//          //Student student =  studentService.register(studentSignUpDto);
//          StudentLoginDto studentLoginDto = new StudentLoginDto("980126378","&7Wddlut");
//
//          Student student1 = studentService.login(studentLoginDto);
//          LoanDto loanDto = new LoanDto("6037697577788133","025",LocalDate.parse("2022-04-03"),
//                  TypeOfLoan.HOUSING);
//            //StudentLoginDto studentLoginDto = new StudentLoginDto("980126378","$3Rrwowl");
//            //        Student student1=studentService.login(studentLoginDto);
//            //studentService.getLoan(student1,loanDto);
//            BaseLoan baseLoan = new BaseLoanImp();
//            List<Loan> loans = baseLoan.findLoan(student1);
//            loans.forEach(System.out::println);
//
////                                SpouseDtoPerson spouseDtoPerson = new SpouseDtoPerson("nafiseh","yari","hengame",
////                    "reza","312","9323151416",LocalDate.parse("1381-05-15"),
////                    City.TEHRAN,true,"0024365890");
////            HouseDto houseDto = new HouseDto(City.TEHRAN,"asdd","14r123");
////            StudentHousingLoanDto studentHousingLoanDto = new StudentHousingLoanDto(false,true);
////            studentService.setTheHotelAndOtherForStudentHousingLoan(student1,studentHousingLoanDto);
////            studentService.setHousingLoan(student1,loanDto,spouseDtoPerson,houseDto);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
//    BillService billService = new BillServiceImp();
//    try {
//    billService.payBill(1);
//    BaseLoan baseLoan = new BaseLoanImp();
//        String cartNumber ="6037697577788133" ;
//                LocalDate expiredDate =LocalDate.parse("2022-04-03");
//                String cvv2 ="025";
//                LoanDtoForCheckCart loanDtoForCheckCart = new LoanDtoForCheckCart(cartNumber,cvv2,expiredDate);
//            Student student = new Student();
//            student.setId(1);
//                List<Loan> loans = baseLoan.findLoan(student);
//    baseLoan.PayTheBill(2,loanDtoForCheckCart,loans);
//
//    }catch (Exception e) {
//        e.printStackTrace();
//    }
    }
}
