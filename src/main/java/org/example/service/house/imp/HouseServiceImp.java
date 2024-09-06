package org.example.service.house.imp;

import org.example.domain.House;
import org.example.dto.HouseDto;
import org.example.service.house.HouseService;

public class HouseServiceImp implements HouseService {
    @Override
    public House save(HouseDto house) {
        House newHouse = House.builder().
         city(house.city())
                .fullAddress(house.fullAddress())
                .houseRentNumber(house.houseRentNumber())
        .build();
        return newHouse;
    }
}
