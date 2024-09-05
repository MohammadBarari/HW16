package org.example.menu;

import org.example.Functions.Functions;
import org.example.domain.Student;
import org.example.service.student.StudentService;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    Functions functions = new Functions(scanner);
    public void start(){
        System.out.println("hi for signUp press 1 , for login press 2");
        int count = functions.checkBoundries1to2.apply(functions.stringIntegerFunction.apply(functions.notAcceptNull.apply(scanner.nextLine())));
        ChoosingLoanMenu choosingLoanMenu = new ChoosingLoanMenu();
        choosingLoanMenu.chooseMenu(count);
    }
}
