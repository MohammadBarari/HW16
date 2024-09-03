package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfMajor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table
public class Student extends Person implements Serializable {

    private String studentNumber;

    private String CollegeName;

    private Integer dateOfEntrance;

    @Enumerated(EnumType.STRING)
    private TypeOfMajor typeOfMajor;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private TypeOfCollege typeOfCollege;

    private Boolean isIntHotel;


}
