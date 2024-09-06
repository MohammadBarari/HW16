package org.example.dto;

import org.example.enumiration.City;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfMajor;

import java.time.LocalDate;

public record StudentSignUpDto(String name,
                               String family
        , String motherName
        , String fatherName
        , String serialNumber
        , String nationalCode
        , LocalDate birthDay
                            , String studentNumber
                                , String CollegeName
                               , Integer dateOfEntrance
                                , TypeOfMajor typeOfMajor
                                , TypeOfCollege typeOfCollege
                                , City city
                               ,Boolean isMarried
                               ,Boolean isInHotel
                               ){}
