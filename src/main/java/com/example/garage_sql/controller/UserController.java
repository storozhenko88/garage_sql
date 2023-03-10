package com.example.garage_sql.controller;
import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.User;
import com.example.garage_sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Cacheable(value = "user", key = "#id")
    public User getUser(@PathVariable int id) {
        return  userService.getUserById(id);
    }

    @GetMapping
    @Cacheable(value = "users")
    public List<User> getAllUser() {
        return  userService.getAllUser();
    }

    @GetMapping("/{id}/cars")
    @Cacheable(value = "cars", key = "#id")
    public List<Car> getUserCars(@PathVariable int id) {
          return userService.getUserCars(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}/car")
    public Car saveCarUser(@PathVariable int id, @RequestBody Car car){
        return userService.saveCarUser(id, car);
    }
    @PutMapping("/{id}")
    public User updateUser (@PathVariable int id,@RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
         userService.deleteUser(id);
    }
}

