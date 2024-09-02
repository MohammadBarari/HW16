package org.example.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Bill {
    private Integer id;

    private LocalDate expiresDate;

    private Long amount;

    private Loan loan;

    private Boolean isPaid;

    private LocalDateTime timeOfPayment;
}
