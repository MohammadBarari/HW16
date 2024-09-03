package org.example.dto;

import org.example.enumiration.City;

import java.time.LocalDate;

public record SpouseDtoPerson(
        String name,
        String family,
        String motherName,
        String fatherName,
        String serialNumber,
        String nationalCode,
        LocalDate birthDay,
        City city,
        Boolean isMarried,
        String spouseNationalCode
) {
}
