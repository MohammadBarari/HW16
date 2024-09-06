package org.example.menu;

import org.example.dto.StudentHousingLoanDto;
import org.example.service.Functions.Functions;
import org.example.domain.Student;
import org.example.dto.StudentSignUpDto;
import org.example.enumiration.City;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfMajor;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.time.LocalDate;
import java.util.Scanner;

public class RegisterMenu {
    public Student token;
    StudentService studentService = new StudentServiceImp();
    Scanner scanner = new Scanner(System.in);
    Functions functions = new Functions(scanner);
    public Student registerMenu() {
        boolean test = false;
        while (!test) {
            System.out.println("welcome ");
            System.out.println("please enter the name : ");
            String name = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the family : ");
            String family = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the motherName : ");
            String motherName = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the fatherName : ");
            String fatherName = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the serialNumber : ");
            String serialNumber = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the nationalCode : ");
            String nationalCode = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the birthDay : ");
            LocalDate birthDay = functions.getCorrectLocalDate.apply(functions.notAcceptNull.apply(scanner.nextLine()));
            System.out.println("please enter the studentNumber : ");
            String studentNumber = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the CollegeName : ");
            String CollegeName = functions.notAcceptNull.apply(scanner.nextLine());
            System.out.println("please enter the dateOfEntrance : ");
            Integer dateOfEntrance = functions.stringIntegerFunction.apply(functions.notAcceptNull.apply(scanner.nextLine()));
            System.out.println("please enter the typeOfMajor : ");
            TypeOfMajor typeOfMajor = functions.typeOfMajorFunction.apply(functions.notAcceptNull.apply(scanner.nextLine()));
            System.out.println("please enter the typeOfCollege : ");
            TypeOfCollege typeOfCollege = functions.typeOfCollegeFunction.apply(functions.notAcceptNull.apply(scanner.nextLine()));
            System.out.println("please enter the city : ");
            City city = functions.cityFunction.apply(functions.notAcceptNull.apply(scanner.nextLine()));
            System.out.println("please say if youre married or not married for married press 1 for not married press 2");
            int marriedCount = functions.checkBoundries1to2.apply(functions.stringIntegerFunction.apply(functions.notAcceptNull.apply(scanner.nextLine())));
            boolean married = marriedCount == 1;
            System.out.println("please say if youre in hotel or not in hotel for hotel press 1 for not  press 2");
            int hotelCount = functions.checkBoundries1to2.apply(functions.stringIntegerFunction.apply(functions.notAcceptNull.apply(scanner.nextLine())));
            boolean isInHotel = marriedCount == 1;
            StudentSignUpDto studentSignUpDto = new StudentSignUpDto(name, family, motherName, fatherName, serialNumber, nationalCode, birthDay, studentNumber, CollegeName, dateOfEntrance, typeOfMajor, typeOfCollege, city,
                    married, isInHotel);
            try {
                token = studentService.register(studentSignUpDto);
                test = true;
                return token;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }
}
