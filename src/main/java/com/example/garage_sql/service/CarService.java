package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.repository.Dao.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car saveCar (Car car){
        return carRepository.save(car);
    }

    public Car getById (int id){
        return carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Car> getAllCars (){
        return carRepository.findAll();
    }


    public Car updateCar (int id, Car car){
        car.setCarId(id);
        return carRepository.save(car);
    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }
}
