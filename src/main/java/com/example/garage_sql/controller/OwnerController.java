package com.example.garage_sql.controller;
import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.Owner;
import com.example.garage_sql.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService userService) {
        this.ownerService = userService;
    }

    @GetMapping("/{id}")
    @Cacheable(value = "user", key = "#id")
    public Owner getUser(@PathVariable int id) {
        return  ownerService.getUserById(id);
    }

    @GetMapping
    @Cacheable(value = "owners")
    public List<Owner> getAllUser() {
        return  ownerService.getAllUser();
    }

    @GetMapping("/{id}/cars")
    @Cacheable(value = "cars", key = "#id")
    public List<Car> getUserCars(@PathVariable int id) {
          return ownerService.getUserCars(id);
    }

    @PostMapping
    public Owner saveUser(@RequestBody Owner user) {
        return ownerService.saveUser(user);
    }

    @PutMapping("/{id}/car")
    public Car saveCarUser(@PathVariable int id, @RequestBody Car car){
        return ownerService.saveCarUser(id, car);
    }
    @PutMapping("/{id}")
    public Owner updateUser (@PathVariable int id, @RequestBody Owner user){
        return ownerService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
         ownerService.deleteUser(id);
    }
}

