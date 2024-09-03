package org.example.domain;

import jakarta.persistence.*;
import org.example.enumiration.City;

import java.io.Serializable;
@Entity
@Table
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private City city;

    private String fullAddress;

    private String houseRentNumber;
}
