package org.example.exeptions;

public class DuplicateStudentException extends Exception{
    public DuplicateStudentException(){
        super("Duplicate Student Exception");
    }
}
