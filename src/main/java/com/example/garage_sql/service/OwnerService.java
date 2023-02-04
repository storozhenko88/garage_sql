package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.Owner;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.Dao.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OwnerService {
    private OwnerRepository userRepository;
    private CarRepository carRepository;

    public OwnerService(OwnerRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }


    public Owner saveUser(Owner user) {
        return userRepository.save(user);
    }

    public Car saveCarUser(int id, Car car) {
        car.setOwnerId(id);
        return carRepository.save(car);
    }

    public Owner updateUser(int id, Owner user) {
        user.setUserId(id);
        return userRepository.save(user);
    }

    public Owner getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Car> getUserCars(int id) {
        return carRepository.findCarByOwnerId(id);
    }

    public List<Owner> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser (int id){
        userRepository.deleteById(id);
    }
}