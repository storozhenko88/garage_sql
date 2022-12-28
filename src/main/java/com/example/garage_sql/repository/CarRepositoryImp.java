package com.example.garage_sql.repository;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.mapper.CarMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepositoryImp implements CarRepository {
    private final JdbcTemplate jdbcTemplate;
    List<Car> cars = new ArrayList<>();

    public CarRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = """
            INSERT INTO public.cars (brand, owner_id)
            VALUES (?, ?) 
            """;
    private final String delete = "DELETE FROM public.cars WHERE car_id = ?";
    private final String update = """
            UPDATE public.cars
            SET brand = ?
            WHERE car_id = ?
            """;

    @Override
    public Car saveCar(Car car) {
        jdbcTemplate.update(save, car.getBrand(), null);
        cars = jdbcTemplate.query("SELECT * FROM public.cars", new CarMapper());
        int lastIndex = cars.size() - 1;
        if (cars.get(lastIndex).getBrand().equals(car.getBrand()))
            return cars.get(lastIndex);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "car not added to the list");
    }

    @Override
    public Car getById(int id) {
        Car car = jdbcTemplate.queryForObject(
                "SELECT * FROM public.cars WHERE car_id = " + id, new CarMapper());
        if (car.getId() == id)
            return car;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "car not found");
    }

    @Override
    public List<Car> getAllCars() {
        cars = jdbcTemplate.query("SELECT * FROM public.cars", new CarMapper());
        if (!cars.isEmpty())
            return cars;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list cars is empty");
    }

    @Override
    public Car updateCar(int id, Car car) {
        jdbcTemplate.update(update, car.getBrand(), id);
        Car auto = getById(id);
        if (auto.getBrand().equals(car.getBrand()))
            return auto;

        throw new ResponseStatusException(HttpStatus.CONFLICT, "car not updated");
    }

    @Override
    public String deleteCar(int id) {
        jdbcTemplate.update(delete, id);
        return "Car was deleted";
    }
}
