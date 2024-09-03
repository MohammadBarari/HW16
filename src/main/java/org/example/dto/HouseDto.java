package org.example.dto;

import org.example.enumiration.City;

public record HouseDto(
        City city,
        String fullAddress,
        String houseRentNumber
) {
}
