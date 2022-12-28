package com.example.garage_sql.repository;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.User;
import com.example.garage_sql.model.UserGarage;
import com.example.garage_sql.repository.Dao.UserRepository;
import com.example.garage_sql.repository.mapper.CarMapper;
import com.example.garage_sql.repository.mapper.UserGarageMapper;
import com.example.garage_sql.repository.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private List<User> users = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();

    public UserRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = """
            INSERT INTO public.users (name, surname)
            VALUES (?, ?) 
            """;
    private final String saveCar = """
            INSERT INTO public.cars (brand, owner_id)
            VALUES (?, ?) 
            """;
    private final String delete = "DELETE FROM public.users WHERE user_id = ?";
    private final String deleteUserCars = "DELETE FROM public.cars WHERE owner_id = ?";
    private final String update = """
            UPDATE public.users
            SET name = ?, surname = ?
            WHERE user_id = ?
            """;

    @Override
    public User getById(int id) {
        User client = jdbcTemplate.queryForObject
                ("SELECT * FROM public.users WHERE user_id = " + id, new UserMapper());
        if (client.getId() == id)
            return client;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");

    }

    @Override
    public List<User> getAllUsers() {
        users = jdbcTemplate.query("SELECT * FROM public.users", new UserMapper());
        if (!users.isEmpty())
            return users;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list users is empty");
    }

    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(save, user.getName(), user.getSurname());
        users = getAllUsers();
        int lastIndex = users.size() - 1;
        if (users.get(lastIndex).getName().equals(user.getName()) &&
                users.get(lastIndex).getSurname().equals(user.getSurname()))
            return users.get(lastIndex);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not added to the list");
    }

    @Override
    public Car saveCarUser(int id, Car car) {
        jdbcTemplate.update(saveCar, car.getBrand(), id);
        cars = jdbcTemplate.query("SELECT * FROM public.cars WHERE owner_id = " + id, new CarMapper());
        int lastIndex = cars.size() - 1;
        if (cars.get(lastIndex).getBrand().equals(car.getBrand()))
            return cars.get(lastIndex);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "car not added to the list");

    }

    @Override
    public List<UserGarage> getUserCars(int id) {
        return jdbcTemplate.query("""
                SELECT 
                public.users.user_id, public.users.name, public.users.surname,
                public.cars.car_id, public.cars.brand
                FROM public.users FULL JOIN public.cars
                ON   public.users.user_id = public.cars.owner_id
                WHERE public.users.user_id =
                """ + id, new UserGarageMapper());
    }

    @Override
    public User updeteUser(int id, User user) {
        jdbcTemplate.update(update, user.getName(), user.getSurname(), id);
        User client = getById(id);
        if (client.getName().equals(user.getName()) && client.getSurname().equals(user.getSurname()))
            return client;

        throw new ResponseStatusException(HttpStatus.CONFLICT, "user not updated");
    }

    @Override
    public String deleteUser(int id) {
        jdbcTemplate.update(delete, id);
        jdbcTemplate.update(deleteUserCars, id);
        return "User was deleted";
    }
}
