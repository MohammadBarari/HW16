package org.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.enumiration.TypeOfLoan;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime dateOfGet; //todo : must be within the dates that was wanted

    private Integer countsInYear; //todo : must be 1 at a year at max

    @ManyToOne
    private Student student;

    private String cv22; //todo : has a fourNumber limit

    private String cartNumber;//todo : must be 16 digits

    private LocalDate cartExpiryDate;

    @OneToMany
    @JoinColumn(name = "bill_id")
    private Set<Bill> bill;

    private TypeOfLoan typeOfLoan;

}
