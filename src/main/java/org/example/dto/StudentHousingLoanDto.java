package org.example.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.example.domain.Student}
 */
public record StudentHousingLoanDto(@NotNull(message = "shoudnt Be null") Boolean isIntHotel,
                                    @NotNull(message = "shoudnt Be null") Boolean isMarried) implements Serializable {
}