package com.example.garage_sql.repository.mapper;

import com.example.garage_sql.model.User;
import com.example.garage_sql.model.UserGarage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGarageMapper implements RowMapper<UserGarage> {
    @Override
    public UserGarage mapRow(ResultSet rows, int rowNum) throws SQLException {
        return UserGarage.builder()
                .user_id(rows.getInt("user_id"))
                .name(rows.getString("name"))
                .surname(rows.getString("surname"))
                .car_id(rows.getInt("car_id"))
                .brand(rows.getString("brand"))
                .build();
    }
}
