package org.example.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.enumiration.TypeOfCollege;
import org.example.enumiration.TypeOfMajor;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Student extends Person{
    private String studentNumber;
    private String CollegeName;
    private Integer dateOfEntrance;
    private TypeOfMajor typeOfMajor;
    private String userName;
    private String password;
    private TypeOfCollege typeOfCollege;
}
