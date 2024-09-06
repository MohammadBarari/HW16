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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private Student student;

    private String cv22; //todo : has a fourNumber limit

    private String cartNumber;//todo : must be 16 digits

    private LocalDate cartExpiryDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private Set<Bill> bill;

    @Enumerated(EnumType.STRING)
    private TypeOfLoan typeOfLoan;

}
