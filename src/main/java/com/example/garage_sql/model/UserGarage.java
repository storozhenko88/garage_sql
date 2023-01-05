package com.example.garage_sql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGarage {

    private int userId;
    private String name;
    private String surname;
    private int carId;
    private String brand;
}
