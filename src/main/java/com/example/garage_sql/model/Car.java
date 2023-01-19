package com.example.garage_sql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cars")
public class Car {
    @Id
    private Integer carId;
    private String brand;
    private Integer ownerId;

    public Car (String brand, Integer ownerId){
        this.brand = brand;
        this.ownerId = ownerId;
    }
}
