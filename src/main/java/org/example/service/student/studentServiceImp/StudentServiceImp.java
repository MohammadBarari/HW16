package org.example.service.student.studentServiceImp;

import net.bytebuddy.asm.Advice;
import org.example.Base.DateOfOpenAndCloseforLoanSignUp;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.dto.StudentLoginDto;
import org.example.dto.StudentSignUpDto;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfLoan;
import org.example.enumiration.TypeOfMajor;
import org.example.exeptions.*;
import org.example.repository.student.StudentRepository;
import org.example.service.loan.tuitionFeeLoan.TuitionFeeLoan;
import org.example.service.loan.tuitionFeeLoan.imp.TuitionFeeLoanImp;
import org.example.service.student.StudentService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
        Character c ;
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
    public void getLoan(Student student, LoanDto loanDto) throws NotQulifiedForThisLoan,
             ErrorItsNotTimeOfSignUp {
        if (validateStudentForTuitionLoan(student)){
            tuitionFeeLoan.getTuitionFeeLoan(student,loanDto);
        }
    }

    @Override
    public boolean validateStudentForTuitionLoan(Student student) throws NotQulifiedForThisLoan,
            ErrorItsNotTimeOfSignUp, CollegeFinished {
        //todo: must do validating student here !!
        LocalDate localDate = LocalDate.now();
        if (ifCollegeIsPaid(student) && checkDate(localDate) && studentStillInCollege(student)
        && checkIfNotDuplicateLoan(student)){
            return true;
        }
        return false;
    }

    private boolean checkIfNotDuplicateLoan(Student student) {
        List<Loan> loans = tuitionFeeLoan.findLoan(student);

    }

    private boolean studentStillInCollege(Student student) throws CollegeFinished{
            int year = LocalDate.now().getYear();
        int count = year - student.getDateOfEntrance();
        if (count< getMaxYearForStudetToBeInCollege(student)){
            return true;
        }
        else {
            throw new CollegeFinished();
        }
    }
    private Integer getMaxYearForStudetToBeInCollege(Student student ){
        if (student.getTypeOfMajor() == TypeOfMajor.KARSHENASIPEYVASTE){
            return 4;
        } else if (student.getTypeOfMajor() == TypeOfMajor.KARSHENASIARSHADNAPEYVASTE ||
        student.getTypeOfMajor() == TypeOfMajor.KRDANI) {
            return 2;
        } else if (student.getTypeOfMajor() == TypeOfMajor.KARSHENASIARSHADPEYVASTE ) {
            return 6;
        } else {
            return 5;
        }

    }
    private boolean checkDate(LocalDate localDate) throws ErrorItsNotTimeOfSignUp {
        if (ifDateIsCorrectForFirstDate(localDate) || ifDateIsCorrectForSecondDate(localDate)){
            return true;
        }
        else {
            throw new ErrorItsNotTimeOfSignUp();
        }
    }


    private boolean ifDateIsCorrectForFirstDate (LocalDate localDate){
        int year = localDate.getYear();
        if (localDate.isAfter(LocalDate.parse(
                String.valueOf(year) + DateOfOpenAndCloseforLoanSignUp.FirstDateOfOpenLoanSignUp
        )) &&
        localDate.isBefore(LocalDate.parse(
                String.valueOf(year) + DateOfOpenAndCloseforLoanSignUp.firstDateOfCloseLoanSignUp
        ))){
            return true;
        }
        return false;
    }

    private boolean ifDateIsCorrectForSecondDate (LocalDate localDate){
        int year = localDate.getYear() ;
        if (localDate.isAfter(LocalDate.parse(
                String.valueOf(year) + DateOfOpenAndCloseforLoanSignUp.secondDateOfOpenLoanSignUp
        )) &&
                localDate.isBefore(LocalDate.parse(
                        String.valueOf(year) + DateOfOpenAndCloseforLoanSignUp.secondDateOfCloseLoanSignUp
                ))){
            return true;
        }
        return false;
    }

    private boolean ifCollegeIsPaid(Student student)throws NotQulifiedForThisLoan{
        if (student.getTypeOfCollege() == TypeOfCollege.ROOZANE || student.getTypeOfCollege() == null){
            throw new NotQulifiedForThisLoan();
        }
        return true;
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
