package org.example.menu;

import org.example.Functions.Functions;
import org.example.domain.Student;
import org.example.dto.StudentLoginDto;
import org.example.service.student.StudentService;
import org.example.service.student.studentServiceImp.StudentServiceImp;

import java.util.Scanner;

public class LoginMenu {
    MainMenu mainMenu = new MainMenu();
    StudentService studentService = new StudentServiceImp();
    Scanner scanner = new Scanner(System.in);
    Functions functions = new Functions(scanner);
    public Student loginMenu(){
        System.out.println("please enter the username");
        String user = functions.notAcceptNull.apply(scanner.nextLine());
        System.out.println("please enter the password");
        String pass = functions.notAcceptNull.apply(scanner.nextLine());
        StudentLoginDto studentLoginDto = new StudentLoginDto(user,pass);
        try {
            return studentService.login(studentLoginDto);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            mainMenu.start();
            return null;
        }
    }
}
