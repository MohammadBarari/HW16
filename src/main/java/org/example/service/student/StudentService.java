package org.example.service.student;

import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.dto.StudentLoginDto;
import org.example.dto.StudentSignUpDto;
import org.example.exeptions.ErrorItsNotTimeOfSignUp;
import org.example.exeptions.NotQulifiedForThisLoan;

public interface StudentService {
    void register(StudentSignUpDto student) throws Exception;

    void settingUserAndPassForStudent(Student student);

    Student login(StudentLoginDto student)throws Exception;

    void getLoan(Student student , LoanDto loanDto) throws NotQulifiedForThisLoan,
            NotQulifiedForThisLoan, ErrorItsNotTimeOfSignUp;

    boolean validateStudentForTuitionLoan(Student student)throws NotQulifiedForThisLoan
            , ErrorItsNotTimeOfSignUp;
    //todo : boolean validateStudentForStudentLoan(Student student);
    // todo : boolean validateStudentForHousingLoan(Student student);

}
