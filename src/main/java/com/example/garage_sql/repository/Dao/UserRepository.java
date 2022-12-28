package com.example.garage_sql.repository.Dao;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.User;
import com.example.garage_sql.model.UserGarage;

import java.util.List;

public interface UserRepository {

    User getById (int id);
    List<User> getAllUsers ();
    User saveUser (User user);
    Car saveCarUser(int id, Car carUser);
    List<UserGarage> getUserCars (int id);
    User updeteUser(int id, User client);
    String deleteUser(int id);

}
