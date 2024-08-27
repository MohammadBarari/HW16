package org.example.service.student.studentServiceImp;

import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.StudentLoginDto;
import org.example.dto.StudentSignUpDto;
import org.example.enumiration.TypeOfLoan;
import org.example.exeptions.DuplicateStudentException;
import org.example.exeptions.NotFoundStudent;
import org.example.repository.student.StudentRepository;
import org.example.service.loan.tuitionFeeLoan.TuitionFeeLoan;
import org.example.service.loan.tuitionFeeLoan.imp.TuitionFeeLoanImp;
import org.example.service.student.StudentService;

import java.util.Random;
import java.util.RandomAccess;
import java.util.function.Function;

public class StudentServiceImp implements StudentService {
    StudentRepository studentRepository;
    private TuitionFeeLoan tuitionFeeLoan = new TuitionFeeLoanImp();
    @Override
    public void register(StudentSignUpDto student) throws DuplicateStudentException {
        if (studentDoesntExist(student)){
            Student student1 = Student.builder().name(student.name())
                    .family(student.family())
                    .motherName(student.motherName())
                    .fatherName(student.fatherName())
                    .serialNumber(student.serialNumber())
            . nationalCode(student.nationalCode())
            .birthDay(student.birthDay())
            .studentNumber(student.studentNumber())
            .CollegeName(student.CollegeName())
            .dateOfEntrance(student.dateOfEntrance())
            .typeOfMajor(student.typeOfMajor())
            .build();
            studentRepository.save(student1);
        }
    }

    @Override
    public void settingUserAndPassForStudent(Student student) {
        Random r = new Random();
        Character c = null;
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i == 0){
                pass.append(integerCharacterFunction.apply(r.nextInt(1,6)));
            }
            else if (i==1){
                pass.append(r.nextInt(9));
            }
            else {
                if (i==2){
                    c = Character.toUpperCase((char)(r.nextInt(26) + 'a'));
                }
                else {
                    c = (char) (r.nextInt(26) + 'a');
                }
                pass.append(c);
            }
        }
        student.setUserName(student.getNationalCode());
        student.setPassword(pass.toString());
    }

    @Override
    public Student login(StudentLoginDto student)throws NotFoundStudent{
        Student student1 = studentRepository.findByUserAndPass(student.username(),student.password());
        if (student1 == null){
            throw new NotFoundStudent();
        }
        return student1;
    }

    @Override
    public void getLoan(Student student) {
        if (validateForGettingLoan(student)){

        }
    }

    @Override
    public boolean validateForGettingLoan(Student student) {
        return false;
    }

    Function<Integer,Character> integerCharacterFunction = integer -> {
        switch (integer){
            case 1 -> {
                return '#';
            }
            case 2 -> {
                return '&';
            }
            case 3 -> {
                return '@';
            }
            case 4 -> {
                return '%';
            }
            case 5 -> {
                return '$';
            }
        }
        return null;
    };

    private boolean studentDoesntExist(StudentSignUpDto student) throws DuplicateStudentException {
        if(studentRepository.findByStudentNumber(student.studentNumber()) != null){
            return true;
        }
        throw new DuplicateStudentException();
    }
}
