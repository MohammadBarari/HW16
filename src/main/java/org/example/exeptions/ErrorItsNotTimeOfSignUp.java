package org.example.exeptions;

public class ErrorItsNotTimeOfSignUp extends Exception{
    public ErrorItsNotTimeOfSignUp(){
        super("Error in SignUp : its not its time of SignUp");
    }
}
