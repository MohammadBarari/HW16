package org.example.service.student.studentServiceImp;

import org.example.domain.Student;
import org.example.dto.StudentSignUpDto;
import org.example.repository.student.StudentRepository;
import org.example.service.student.StudentService;

public class StudentServiceImp implements StudentService {
    StudentRepository studentRepository;

    @Override
    public void register(StudentSignUpDto student) {
        if (studentDoesntExist(student)){
            Student student1 = Student.builder().name(student.name())
                    .family(student.family())
                    .
        }
    }

    @Override
    public void settingUserAndPassForStudent(Student student) {

    }
}
