package org.example.service.student;

import org.example.domain.Student;
import org.example.dto.*;
import org.example.exeptions.CollegeFinished;
import org.example.exeptions.ErrorItsNotTimeOfSignUp;
import org.example.exeptions.NotQulifiedForThisLoan;

public interface StudentService {
    Student register(StudentSignUpDto student) throws Exception;

    void settingUserAndPassForStudent(Student student);

    Student login(StudentLoginDto student)throws Exception;

    void getLoan(Student student , LoanDto loanDto) throws NotQulifiedForThisLoan,
            NotQulifiedForThisLoan, ErrorItsNotTimeOfSignUp, CollegeFinished;

    void setHousingLoan(Student student , LoanDto loanDto, SpouseDtoPerson spouseDtoPerson,
    HouseDto houseDto) throws NotQulifiedForThisLoan;

    boolean validateStudentForGettingHousingLoan(Student student) throws NotQulifiedForThisLoan;

    boolean validateStudentForTuitionLoan(Student student)throws NotQulifiedForThisLoan
            , ErrorItsNotTimeOfSignUp, CollegeFinished;

    boolean validateStudentForStudentLoan(Student student) throws
            CollegeFinished, NotQulifiedForThisLoan, ErrorItsNotTimeOfSignUp;

    // todo : boolean validateStudentForHousingLoan(Student student);

    void PayTheBills(Student student);

    //todo: check whether if student is in three group that the question needed


    Student findByNationalCode(String nationalCode);
}
