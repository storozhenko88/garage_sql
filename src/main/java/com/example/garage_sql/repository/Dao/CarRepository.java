package com.example.garage_sql.repository.Dao;

import com.example.garage_sql.model.Car;
import java.util.List;

public interface CarRepository {

    Car saveCar(Car car);
    Car getById(int id);
    List<Car> getAllCars();
    Car updateCar(int id, Car auto);
    String deleteCar(int id);
}
