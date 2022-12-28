package com.example.garage_sql.repository.mapper;

import com.example.garage_sql.model.Car;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Car.builder()
                .id(rows.getInt("car_id"))
                .brand(rows.getString("brand"))
                .owner_id(rows.getInt("owner_id"))
                .build();
    }
}
