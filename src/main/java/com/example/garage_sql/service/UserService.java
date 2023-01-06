package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.User;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.Dao.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private CarRepository carRepository;

    public UserService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Car saveCarUser(int id, Car car) {
        car.setOwnerId(id);
        return carRepository.save(car);
    }

    public User updateUser(int id, User user) {
        user.setUserId(id);
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Car> getUserCars(int id) {
        return carRepository.findCarByOwnerId(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser (int id){
        userRepository.deleteById(id);
    }
}