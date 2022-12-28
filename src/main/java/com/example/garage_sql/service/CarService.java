package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.repository.Dao.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car saveCar (Car car){
        return carRepository.saveCar(car);
    }

    public Car getById (int id){
        return carRepository.getById(id);
    }

    public List<Car> getAllCars (){
        return carRepository.getAllCars();
    }

    public Car updateCar (int id, Car car){
        return carRepository.updateCar(id, car);
    }

    public String deleteCar(int id) {
        return carRepository.deleteCar(id);
    }
}
