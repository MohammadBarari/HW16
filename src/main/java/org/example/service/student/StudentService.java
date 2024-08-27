package org.example.service.student;

import org.example.domain.Student;
import org.example.dto.StudentSignUpDto;

public interface StudentService {
    void register(StudentSignUpDto student) throws Exception;

    void settingUserAndPassForStudent(Student student);


}
