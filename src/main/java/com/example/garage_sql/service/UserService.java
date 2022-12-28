package com.example.garage_sql.service;


import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.User;
import com.example.garage_sql.model.UserGarage;
import com.example.garage_sql.repository.Dao.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public Car saveCarUser(int id, Car car) {
        return userRepository.saveCarUser(id, car);
    }

    public User updeteUser(int id, User user) {
        return userRepository.updeteUser(id, user);
    }

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public List<UserGarage> getUserCars(int id) {
        return userRepository.getUserCars(id);
    }

    public List<User> getAllUser() {
        return userRepository.getAllUsers();
    }

    public String deleteUser (int id){
        return userRepository.deleteUser(id);
    }
}