package org.example.repository.student;

import org.example.domain.Student;

public interface StudentRepository {
    void save(Student student);
    Student findByStudentNumber(String studentNumber);
}
