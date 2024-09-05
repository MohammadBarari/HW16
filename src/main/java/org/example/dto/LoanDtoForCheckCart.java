package org.example.dto;

import java.time.LocalDate;

public record LoanDtoForCheckCart(
        String cartNumber,
        String cvv2,
        LocalDate expiresDate
) {
}
