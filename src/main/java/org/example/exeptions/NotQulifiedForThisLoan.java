package org.example.exeptions;

public class NotQulifiedForThisLoan extends Exception{
    public NotQulifiedForThisLoan(){
        super("not qualified for this loan");
    }
}
