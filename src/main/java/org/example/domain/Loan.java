package org.example.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.enumiration.TypeOfLoan;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Loan {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate dateOfGet; //todo : must be within the dates that was wanted

    private Integer countsInYear; //todo : must be 1 at a year at max

    @ManyToOne
    private Student student;

    private String cv22; //todo : has a fourNumber limit

    private String cartNumber;//todo : must be 16 digits

    private LocalDate cartExpiryDate;

    @OneToMany
    private Set<Bill> bill;

    private TypeOfLoan typeOfLoan;

}
