package org.example.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
}
