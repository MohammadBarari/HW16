package org.example.service.student;

import org.example.domain.Student;
import org.example.dto.StudentLoginDto;
import org.example.dto.StudentSignUpDto;

public interface StudentService {
    void register(StudentSignUpDto student) throws Exception;

    void settingUserAndPassForStudent(Student student);

    Student login(StudentLoginDto student)throws Exception;

    void getLoan(Student student);

    boolean validateForGettingLoan(Student student);


}
