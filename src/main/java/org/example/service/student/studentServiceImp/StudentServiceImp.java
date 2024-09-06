package org.example.service.student.studentServiceImp;

import org.example.Base.DateOfOpenAndCloseforLoanSignUp;
import org.example.domain.Loan;
import org.example.domain.Person;
import org.example.domain.Student;
import org.example.dto.*;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfLoan;
import org.example.enumiration.TypeOfMajor;
import org.example.exeptions.*;
import org.example.repository.student.StudentRepository;
import org.example.repository.student.imp.StudentRepositoryImp;
import org.example.service.house.HouseService;
import org.example.service.house.imp.HouseServiceImp;
import org.example.service.loan.housingLoan.HousingLoanService;
import org.example.service.loan.housingLoan.imp.HousingLoanServiceImp;
import org.example.service.loan.studentLoan.StudentLoanService;
import org.example.service.loan.studentLoan.imp.StudentLoanServiceImp;
import org.example.service.loan.tuitionFeeLoan.TuitionFeeLoanService;
import org.example.service.loan.tuitionFeeLoan.imp.TuitionFeeLoanServiceImp;
import org.example.service.student.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class StudentServiceImp implements StudentService {
    StudentRepository studentRepository = new StudentRepositoryImp();
    private TuitionFeeLoanService tuitionFeeLoanService = new TuitionFeeLoanServiceImp();
    private StudentLoanService studentLoanService =  new StudentLoanServiceImp();
    private HousingLoanService housingLoanService =  new HousingLoanServiceImp();
    private HouseService houseService =  new HouseServiceImp();

    @Override
    public Student register(StudentSignUpDto student) throws DuplicateStudentException {
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
                    .typeOfCollege(student.typeOfCollege())
                    .city(student.city())
                    .isMarried(student.isMarried())
                    .isIntHotel(student.isInHotel())
            .build();
            settingUserAndPassForStudent(student1);
            saveTheStudent(student1);
            return student1;}
        return null;
    }

    @Override
    public void settingUserAndPassForStudent(Student student) {
        Random r = new Random();
        Character c ;
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i == 0){
                pass.append(integerCharacterFunction.apply(
                        ThreadLocalRandom.current().nextInt(1, 6)));
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
            ErrorItsNotTimeOfSignUp, CollegeFinished {
        switch (loanDto.typeOfLoan()){
            case TUITION -> {
                if (validateStudentForTuitionLoan(student)){
                    tuitionFeeLoanService.getTuitionFeeLoan(student,loanDto);
                }
            }
            case STUDENTLOAN -> {
                if (validateStudentForStudentLoan(student)){
                    studentLoanService.getStudentLoan(student,loanDto);
                }
            }
        }
    }

    @Override
    public void setHousingLoan(Student student, LoanDto loanDto
            , SpouseDtoPerson spouseDtoPerson,
    HouseDto houseDto) throws NotQulifiedForThisLoan, CollegeFinished, ErrorItsNotTimeOfSignUp {
        student.setSpouseNationalCode(spouseDtoPerson.spouseNationalCode());
        if (validateStudentForGettingHousingLoan(student)){
            Student student1 = findByNationalCode(spouseDtoPerson.nationalCode());
            if ( student1 == null ||student1.getStudentNumber() == null){
                SaveSpouse(spouseDtoPerson , student.getNationalCode());
            }
            houseService.save(houseDto);
            housingLoanService.getHousingLoan(student,loanDto);
        }else {
            throw new NotQulifiedForThisLoan();
        }
    }
    public void setTheHotelAndOtherForStudentHousingLoan(Student student, StudentHousingLoanDto studentHousingLoanDto){
        student.setIsMarried(studentHousingLoanDto.isMarried());
        student.setIsIntHotel(studentHousingLoanDto.isIntHotel());
        studentRepository.studentUpdate(student);
    }
    private void SaveSpouse(SpouseDtoPerson spouseDtoPerson, String spouseNationalCode) {
        Person person = new Person();
        person.setName(spouseDtoPerson.name());
        person.setFamily(spouseDtoPerson.family());
        person.setMotherName(spouseDtoPerson.motherName());
        person.setFatherName(spouseDtoPerson.fatherName());
        person.setSerialNumber(spouseDtoPerson.serialNumber());
        person.setNationalCode(spouseDtoPerson.nationalCode());
        person.setBirthDay(spouseDtoPerson.birthDay());
        person.setSpouseNationalCode(spouseNationalCode);
        savePerson(person);
    }

    public Student saveTheStudent(Student student){
        studentRepository.save(student);
        return student;
    }
    public Person savePerson(Person person){
        //todo: must save person here ;
        studentRepository.savePerson(person);
        return person;
    }

    @Override
    public boolean validateStudentForGettingHousingLoan(Student student) throws NotQulifiedForThisLoan, ErrorItsNotTimeOfSignUp, CollegeFinished {
        if (checkDate(LocalDate.now()) &&
                studentStillInCollege(student) && checkIfNotDuplicateLoanForTuition(student,TypeOfLoan.HOUSING)
                &&student.getIsMarried() && student.getIsIntHotel() && checkSpouse(student)){
            return true;
        }
        throw new NotQulifiedForThisLoan();
    }
    private Boolean checkSpouse(Student student)  {
        Student spouse = checkIfSpouseExistForHousingLoan(student.getSpouseNationalCode());
        if (Objects.isNull(spouse) || spouse.getStudentNumber() == null){
            return true;
        }else {
            List<Loan> loans = tuitionFeeLoanService.findLoan(spouse);
            for (Loan loan : loans){
                if (loan.getTypeOfLoan() == TypeOfLoan.HOUSING){
                    return false;
                }
            }
            return true;
        }
    }
    private Student checkIfSpouseExistForHousingLoan(String nationalCode){
        Student spouse = findByNationalCode(nationalCode);
        if ( spouse == null ||spouse.getStudentNumber() == null){
            return null;
        }
        return spouse;
    }
    @Override
    public Student findByNationalCode(String nationalCode) {
        //todo: must fix this class
        return studentRepository.findStudentByNationalCode(nationalCode);
    }

    @Override
    public boolean validateStudentForTuitionLoan(Student student) throws NotQulifiedForThisLoan,
            ErrorItsNotTimeOfSignUp, CollegeFinished {
        //todo: must do validating student here !!
        if (ifCollegeIsPaid(student) && checkStudentThatCanGetLoan(student,TypeOfLoan.TUITION)){
            return true;
        }
        return false;
    }
    public boolean validateStudentForStudentLoan(Student student) throws
            CollegeFinished, NotQulifiedForThisLoan, ErrorItsNotTimeOfSignUp {
        if (checkStudentThatCanGetLoan(student,TypeOfLoan.STUDENTLOAN)){
            return true;
        }
        else return false;
    }
    //todo: must pay the bills here
    @Override
    public void PayTheBills(Student student) {

    }
    //todo: pay bills finished
    private boolean checkStudentThatCanGetLoan(Student student , TypeOfLoan typeOfLoan) throws CollegeFinished
            , NotQulifiedForThisLoan, ErrorItsNotTimeOfSignUp {
        if (checkDate(LocalDate.now()) && studentStillInCollege(student)
                && checkIfNotDuplicateLoanForTuition(student ,typeOfLoan)){
            return true;
        }
        return false;
    }
    private boolean checkIfNotDuplicateLoanForTuition(Student student , TypeOfLoan typeOfLoan) throws NotQulifiedForThisLoan {
        List<Loan> loans = tuitionFeeLoanService.findLoan(student);
        switch (typeOfLoan){
            case TUITION,STUDENTLOAN ->{
                for (Loan loan : loans) {
                    if (loan.getTypeOfLoan() == TypeOfLoan.HOUSING){
                        throw new NotQulifiedForThisLoan();
                    }else if (loan.getTypeOfLoan() == TypeOfLoan.STUDENTLOAN || loan.getTypeOfLoan() == TypeOfLoan.TUITION ){
                        if (LocalDate.now().getYear() == loan.getDateOfGet().getYear()){
                            if (!(ifDateIsCorrectForSecondDate(loan.getDateOfGet().toLocalDate()) && ifDateIsCorrectForFirstDate(LocalDate.now()))){
                                throw new NotQulifiedForThisLoan();
                            }
                        }
                        if (LocalDate.now().getYear()  == loan.getDateOfGet().getYear() + 1){
                            if (ifDateIsCorrectForSecondDate(LocalDate.now()) && ifDateIsCorrectForFirstDate(loan.getDateOfGet().toLocalDate())){
                                throw new NotQulifiedForThisLoan();
                            }
                        }
                    }
                }
            }
            case HOUSING -> {
                for (Loan loan : loans) {
                    if (loan.getTypeOfLoan() == TypeOfLoan.HOUSING) {
                        throw new NotQulifiedForThisLoan();
                    }
                }
            }

        }
        //todo : it will find all loans that student got

        //todo: we must check if the spouse of the student get house loan or not if yes we block this student of getting house loan
        return true;
    }

    private boolean studentStillInCollege(Student student) throws CollegeFinished{
            int year = LocalDate.now().getYear();
        int count = year - student.getDateOfEntrance();
        if (count< getMaxYearForStudentToBeInCollege(student)){
            return true;
        }
        else {
            throw new CollegeFinished();
        }
    }

    private Integer getMaxYearForStudentToBeInCollege(Student student ){
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
                (year) + DateOfOpenAndCloseforLoanSignUp.FirstDateOfOpenLoanSignUp
        )) &&
        localDate.isBefore(LocalDate.parse(
                (year) + DateOfOpenAndCloseforLoanSignUp.firstDateOfCloseLoanSignUp
        ))){
            return true;
        }
        return false;
    }

    private boolean ifDateIsCorrectForSecondDate (LocalDate localDate){
        int year = localDate.getYear() ;
        if (localDate.isAfter(LocalDate.parse(
                (year) + DateOfOpenAndCloseforLoanSignUp.secondDateOfOpenLoanSignUp
        )) &&
                localDate.isBefore(LocalDate.parse(
                        (year) + DateOfOpenAndCloseforLoanSignUp.secondDateOfCloseLoanSignUp
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
        if(studentRepository.findByStudentNumber(student.studentNumber()) == null){
            return true;
        }
        throw new DuplicateStudentException();
    }

}
