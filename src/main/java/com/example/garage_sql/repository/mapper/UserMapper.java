package com.example.garage_sql.repository.mapper;

import com.example.garage_sql.model.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rows, int rowNum) throws SQLException {
        return User.builder()
                .id(rows.getInt("user_id"))
                .name(rows.getString("name"))
                .surname((rows.getString("surname")))
                .build();
    }
}
