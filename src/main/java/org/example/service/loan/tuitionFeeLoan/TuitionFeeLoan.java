package org.example.service.loan.tuitionFeeLoan;

import org.example.domain.Student;

public interface TuitionFeeLoan {
    void registerStudent();
    void validateStudent(Student student);

}
