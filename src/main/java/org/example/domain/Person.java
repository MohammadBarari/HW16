package org.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.enumiration.City;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String family;
    private String motherName;
    private String fatherName;
    private String serialNumber;
    private String nationalCode;
    private LocalDate birthDay;
    private City city;
    private Boolean isMarried;
    private String spouseNationalCode;

    @OneToOne
    private House house;
}
