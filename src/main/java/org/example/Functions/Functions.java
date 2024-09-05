package org.example.Functions;

import org.example.enumiration.City;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfLoan;
import org.example.enumiration.TypeOfMajor;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {
    Scanner scanner ;
    public Functions(Scanner scanner){
        this.scanner=  scanner;
    }

    public Function<String , String> notAcceptNull = string -> {
        while (true){
            if (string.isEmpty()){
                System.out.println("cant be empty");
                string = scanner.nextLine();
            }else {
                return string;
            }
            }
    };
    public Function<String, LocalDate> getCorrectLocalDate = string -> {
        LocalDate localDate ;
        try {
            localDate = LocalDate.parse(string);
            return localDate;
        }catch (Exception e){
            System.out.println("please enter the date correctly !!! ");
        }
        return null;
    };
    public Function<String,Integer> stringIntegerFunction = string -> {
        boolean valid = false;
        Integer outPut = null;
        while (!valid){
            try {
                outPut = Integer.parseInt(string);
                valid = true;
            }catch (Exception e){
                System.out.println("please enter the number correctly !!! ");
                string = scanner.nextLine();
            }
        }
        return outPut;
    };

    public Function<String  ,  TypeOfMajor> typeOfMajorFunction = s -> {
        while (true){
            try {
                return TypeOfMajor.valueOf(s);
            }catch (Exception e){
                System.out.println("please enter the type of major corectly!!! ");
                s = notAcceptNull.apply(s);
            }
        }
    };
    public Function<String , TypeOfCollege > typeOfCollegeFunction = s -> {
        while (true){
            try {
                return TypeOfCollege.valueOf(s);
            }catch (Exception e){
                System.out.println("please enter the type of college corectly!!! ");
            }
        }
    };
    public Function<String , City> cityFunction = s -> {
        while (true){
            try {
                City city = City.valueOf(s);
                return city;
            }catch (Exception e){
                System.out.println("please enter the city corectly!!! ");
            }
        }
    };
    public Function<Integer,Integer> checkBoundries1to2 = integer -> {
        while (true){
            if (integer == 1 || integer == 2){
                return integer;
            }else {
                System.out.println("please enter the number correctly !!! ");
                integer = stringIntegerFunction.apply(notAcceptNull.apply(scanner.nextLine()));
            }
        }
    };
    public Function<String  , TypeOfLoan> typeOfLoanCheck = s -> {
        while (true){
            try {
                return TypeOfLoan.valueOf(s);
            }catch (Exception e){
                System.out.println("please enter the type of major corectly!!! ");
            }
        }
    };
    public Function<String , Boolean > checkCvv2Length = string -> {
        for (int i = 0; i < string.length(); i++) {
            try {
                int c = Integer.parseInt(String.valueOf(string.charAt(i)));
            }catch (Exception e){
                return false;
            }
        }
        if (string.length() >4)
            return false;
        return true;
    };

    public Function<String , String> checkCvv2 = string -> {
        boolean valid = false;
        while (!valid){
            valid = checkCvv2Length.apply(string);
            if (!valid){
                System.out.println("please enter the cvv2 correctly!!! ");
                string = notAcceptNull.apply(scanner.nextLine());
            }
        }
        return string;
    };
    public BiFunction<Integer,Integer,Integer> checkBoundriesForId = (number, bound) ->{
        while (true){
            if (number < bound){
                return number;
            }else {
                System.out.println("please enter the number correctly!!! ");
                number = stringIntegerFunction.apply(notAcceptNull.apply(scanner.nextLine()));
            }
        }
    } ;
}
