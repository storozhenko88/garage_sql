package com.example.garage_sql.controller;
import com.example.garage_sql.model.Car;
import com.example.garage_sql.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    @Cacheable(value = "car", key = "#id")
    public Car getById (@PathVariable int id){
        return carService.getById(id);
    }

    @GetMapping
    @Cacheable(value = "cars")
    public List<Car> getAllCars (){
        return carService.getAllCars();
    }

    @PostMapping
    public Car saveCar (@RequestBody Car car){
        return carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar (@PathVariable int id, @RequestBody Car car){
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar (@PathVariable int id){
         carService.deleteCar(id);
    }
}

