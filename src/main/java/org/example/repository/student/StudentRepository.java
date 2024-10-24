package org.example.repository.student;

import org.example.domain.Person;
import org.example.domain.Student;

public interface StudentRepository {
    void save(Student student);
    Student findByStudentNumber(String studentNumber);
    Student findByUserAndPass(String user, String pass);
    void studentUpdate(Student student);
    Student findStudentByNationalCode(String nationalCode);
    void savePerson(Person person);
}
