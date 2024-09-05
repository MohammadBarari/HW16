package org.example.menu;


import org.example.Functions.Functions;
import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.HouseDto;
import org.example.dto.LoanDto;
import org.example.dto.LoanDtoForCheckCart;
import org.example.dto.SpouseDtoPerson;
import org.example.enumiration.City;
import org.example.enumiration.TypeOfLoan;
import org.example.service.loan.base.BaseLoan;
import org.example.service.loan.base.BaseLoanImp;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ChoosingLoanMenu {
    Scanner scanner = new Scanner(System.in);
    Functions functions = new Functions(scanner);
    StudentService studentService = new StudentServiceImp();
    BaseLoan baseLoan = new BaseLoanImp();
    public void chooseMenu(int i) {
        Student student;
        switch (i){
            case 1 -> {
                RegisterMenu registerMenu = new RegisterMenu();
                student= registerMenu.registerMenu();
            }
            case 2 -> {
                LoginMenu loginMenu = new LoginMenu();
                student= loginMenu.loginMenu();
            }
            default -> {
                student = null;

            }
        }
        if (student == null){

        }
        System.out.println("please ensert");
        System.out.println("Repaiment 1 ");
        System.out.println("Getting loan 2");
        int choose = functions.checkBoundries1to2.apply(functions.stringIntegerFunction.apply(functions.notAcceptNull.apply(scanner.nextLine())));
        switch (choose) {
            case 2-> showTheLoansOptions(student);
            case 1 -> ShowAllLoansForStudent(student);
        }
    }
    public void showTheLoansOptions(Student student) {
        System.out.println("Choose loan type");
        TypeOfLoan typeOfLoan = functions.typeOfLoanCheck.apply(functions.notAcceptNull.apply(scanner.nextLine()));
        System.out.println("please insert your cartNumber");
        String cartNumber = functions.notAcceptNull.apply(scanner.nextLine());
        System.out.println("please enter cvv2");
        String cvv2 = functions.checkCvv2.apply(scanner.nextLine());
        LocalDate expiresDate = functions.getCorrectLocalDate.apply(functions.notAcceptNull.apply(scanner.nextLine()));
        switch (typeOfLoan) {
            case HOUSING -> {
                LoanDto loanDto = new LoanDto(
                        cartNumber,
                        cvv2,
                        expiresDate,
                        TypeOfLoan.HOUSING
                );
                System.out.println("please enter the spouse name");
                String name = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("please enter the spouse family");
                String family = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("please enter the spouse motherName");
                String motherName = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("please enter the spouse fatherName");
                String fatherName = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("please enter the spouse serialNumber");
                String serialNumber = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("please enter the spouse nationalCode");
                String nationalCode = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("please enter the spouse birthDay");
                LocalDate birthDay= functions.getCorrectLocalDate.apply(functions.notAcceptNull.apply(scanner.nextLine()));
                System.out.println("please enter the spouse city");
                City city = functions.cityFunction.apply(functions.notAcceptNull.apply(scanner.nextLine()));
                Boolean isMarried= true;
                System.out.println("please enter the spouse spouseNationalCode");
                String spouseNationalCode  = student.getNationalCode();
                SpouseDtoPerson spouseDtoPerson = new SpouseDtoPerson(name,family,motherName,fatherName,
                        serialNumber,nationalCode,birthDay,city,isMarried,spouseNationalCode);
                System.out.println("insert house fullAddress");
                String fullAddress = functions.notAcceptNull.apply(scanner.nextLine());
                System.out.println("insert house houseRentNumber");
                String houseRentNumber = functions.notAcceptNull.apply(scanner.nextLine());
                HouseDto houseDto = new HouseDto(spouseDtoPerson.city(),fullAddress,houseRentNumber);
                try {
                    studentService.setHousingLoan(student, loanDto, spouseDtoPerson, houseDto);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case STUDENTLOAN -> {
                LoanDto loanDto = new LoanDto(
                        cartNumber,
                        cvv2,
                        expiresDate,
                        TypeOfLoan.STUDENTLOAN
                );
                try {
                    studentService.getLoan(student, loanDto);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case TUITION ->{
                LoanDto loanDto = new LoanDto(
                        cartNumber,
                        cvv2,
                        expiresDate,
                        TypeOfLoan.TUITION
                );
                try {
                    studentService.getLoan(student,loanDto);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
    }
    public void ShowAllLoansForStudent(Student student) {
        boolean finish = false;
        while (!finish) {

            List<Loan> loans = baseLoan.findLoan(student);
            if (loans == null) {
                System.out.println(" you dont have any loan");
            } else {
                AtomicInteger atomicInteger = new AtomicInteger(1);
                loans.forEach(loan -> {
                    System.out.println(atomicInteger.getAndIncrement() + ":  " + "loan id : " + loan.getId() + "  = " + loan.getTypeOfLoan());
                });
                System.out.println("write the id that you want to pay its bills :");
                int id = functions.checkBoundriesForId
                        .apply(functions.stringIntegerFunction
                                .apply(functions.notAcceptNull.apply(scanner.nextLine())), loans.size());
                System.out.println("all paid bills :");
                List<Bill> bills = baseLoan.allPaidBillForALoan(loans.get(id - 1).getId());
                if (bills != null) {
                    bills.forEach(bill -> {
                        System.out.println("bill id : " + bill.getId() + " ->>>>>>>> " + bill.getAmount() + "timeOf payment : " + bill.getTimeOfPayment());
                        System.out.println("------------------------");
                    });
                    System.out.println("++++++++++++++++++++++++++++++++");
                }
                System.out.println("all non paid bills");
                List<Bill> allNonPaidBills = baseLoan.allNonPaidBillForALoan(loans.get(0).getId());
                if (allNonPaidBills != null) {
                    allNonPaidBills.forEach(bill -> {
                        System.out.println("bill id : " + bill.getId() + " ->>>>>>>> " + bill.getAmount() + "timeOf expire : " + bill.getExpiresDate());
                        System.out.println("------------------------");
                    });
                    System.out.println("for pay the bill just enter its id ");
                    Integer billIdForPay = functions.stringIntegerFunction
                            .apply(functions.notAcceptNull.apply(scanner.nextLine()));
                    System.out.println("please enter the cart ");
                    String cart = functions.notAcceptNull.apply(scanner.nextLine());
                    System.out.println("please enter the expiresDate ");
                    LocalDate localDate = functions.getCorrectLocalDate.apply(functions.notAcceptNull.apply(scanner.nextLine()));
                    System.out.println("please enter the cvv2 ");
                    String cvv2 = functions.checkCvv2.apply(functions.notAcceptNull.apply(scanner.nextLine()));
                    LoanDtoForCheckCart loanDtoForCheckCart = new LoanDtoForCheckCart(cart, cvv2, localDate);
                    try {
                        baseLoan.PayTheBill(billIdForPay, loanDtoForCheckCart, loans);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("do you want to exist press 1");
            int input = functions.checkBoundries1to2.apply(functions.stringIntegerFunction.apply(functions.notAcceptNull.apply(scanner.nextLine())));
        }
    }

}
