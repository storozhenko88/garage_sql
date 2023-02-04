package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.Owner;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.Dao.OwnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("dev")
public class OwnerServiceTest {

    private OwnerRepository userRepository = Mockito.mock(OwnerRepository.class);
    private CarRepository carRepository = Mockito.mock(CarRepository.class);
    private OwnerService ownerService;
    private CarService carService;

    @Before
    public void init() {
        ownerService = new OwnerService(userRepository, carRepository);
        carService = new CarService(carRepository);
    }
    @Test
    public void saveUserTest() {
        Owner dmytro = Owner.builder()
                .userId(1)
                .name("Dmytro")
                .surname("Storozhenko").build();
        Mockito.when(userRepository.save(any())).thenReturn(dmytro);
        Owner user = ownerService.saveUser(new Owner());
        Assertions.assertEquals(user, dmytro);
    }
    @Test
    public void saveCarUserTest() {
        Car bmw = Car.builder()
                .carId(1)
                .brand("bmw")
                .ownerId(1).build();
        Mockito.when(carRepository.save(any())).thenReturn(bmw);
        Car car = ownerService.saveCarUser(anyInt(), new Car("bmw", 2));
        Assertions.assertEquals(bmw, car);
    }
    @Test
    public void updateUserTest() {
        Owner kiril = Owner.builder()
                .userId(2)
                .name("Kiril")
                .surname("Ivanov").build();
        Mockito.when(userRepository.save(any())).thenReturn(kiril);
        Owner user = ownerService.updateUser(anyInt(), new Owner());
        Assertions.assertEquals(kiril, user);
    }
    @Test
    public void getUserByIdTest() {
        Owner oleg = Owner.builder()
                .userId(3)
                .name("Oleg")
                .surname("Popovich").build();
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(oleg));
        Owner user = ownerService.getUserById(anyInt());
        Assertions.assertEquals(oleg, user);
    }
    @Test
    public void getUserByIdNotFoundTest() {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = Assertions.assertThrows(
                ResponseStatusException.class, () -> ownerService.getUserById(anyInt()));
        Assertions.assertEquals("404 NOT_FOUND", responseStatusException.getMessage());
    }
    @Test
    public void getUserCarsTest() {
        List<Car> cars = List.of(
                new Car("audi", 1),
                new Car("lexus", 1));

        Mockito.when(carRepository.findCarByOwnerId(anyInt())).thenReturn(cars);
        List<Car> userCars = ownerService.getUserCars(anyInt());
        Assertions.assertEquals(cars, userCars);
    }
    @Test
    public void getAllUserTest() {
        List<Owner> users = List.of(
                new Owner(1, "Oly", "Novikova"),
                new Owner(2, "Valy", "Petrova"));
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<Owner> usersList = ownerService.getAllUser();
        Assertions.assertEquals(usersList, users);
    }
}
