package org.example.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanDto(String cartNumber, String cvv2, LocalDate expiresDate) {

}
