package com.example.garage_sql.repository.Dao;

import com.example.garage_sql.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findAll();

    List<Car> findCarByOwnerId (int id);
}
