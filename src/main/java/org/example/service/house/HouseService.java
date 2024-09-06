package org.example.service.house;

import org.example.domain.House;
import org.example.dto.HouseDto;

public interface HouseService {
    House save(HouseDto house);
}
