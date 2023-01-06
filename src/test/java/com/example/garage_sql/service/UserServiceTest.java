package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.User;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.Dao.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private CarRepository carRepository = Mockito.mock(CarRepository.class);
    private UserService userService;
    private CarService carService;

    @Before
    public void init() {
        userService = new UserService(userRepository, carRepository);
        carService = new CarService(carRepository);
    }
    @Test
    public void saveUserTest() {
        User dmytro = User.builder()
                .userId(1)
                .name("Dmytro")
                .surname("Storozhenko").build();
        Mockito.when(userRepository.save(any())).thenReturn(dmytro);
        User user = userService.saveUser(new User());
        Assertions.assertEquals(user, dmytro);
    }
    @Test
    public void saveCarUserTest() {
        Car bmw = Car.builder()
                .carId(1)
                .brand("bmw")
                .ownerId(1).build();
        Mockito.when(carRepository.save(any())).thenReturn(bmw);
        Car car = userService.saveCarUser(anyInt(), new Car());
        Assertions.assertEquals(bmw, car);
    }
    @Test
    public void updateUserTest() {
        User kiril = User.builder()
                .userId(2)
                .name("Kiril")
                .surname("Ivanov").build();
        Mockito.when(userRepository.save(any())).thenReturn(kiril);
        User user = userService.updateUser(anyInt(), new User());
        Assertions.assertEquals(kiril, user);
    }
    @Test
    public void getUserByIdTest() {
        User oleg = User.builder()
                .userId(3)
                .name("Oleg")
                .surname("Popovich").build();
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(oleg));
        User user = userService.getUserById(anyInt());
        Assertions.assertEquals(oleg, user);
    }
    @Test
    public void getUserByIdNotFoundTest() {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = Assertions.assertThrows(
                ResponseStatusException.class, () -> userService.getUserById(anyInt()));
        Assertions.assertEquals("404 NOT_FOUND", responseStatusException.getMessage());
    }
    @Test
    public void getUserCarsTest() {
        List<Car> cars = List.of(
                new Car(1, "audi", 1),
                new Car(2, "lexus", 1));

        Mockito.when(carRepository.findCarByOwnerId(anyInt())).thenReturn(cars);
        List<Car> userCars = userService.getUserCars(anyInt());
        Assertions.assertEquals(cars, userCars);
    }
    @Test
    public void getAllUserTest() {
        List<User> users = List.of(
                new User(1, "Oly", "Novikova"),
                new User(2, "Valy", "Petrova"));
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> usersList = userService.getAllUser();
        Assertions.assertEquals(usersList, users);
    }
}
