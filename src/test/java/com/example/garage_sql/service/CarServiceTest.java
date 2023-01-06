package com.example.garage_sql.service;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.repository.Dao.CarRepository;
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
public class CarServiceTest {

    private CarRepository carRepository = Mockito.mock(CarRepository.class);
    private CarService carService;

    @Before
    public void init() {
        carService = new CarService(carRepository);
    }

    @Test
    public void saveCarTest() {
        Car audi = Car.builder()
                .carId(1)
                .brand("audi")
                .ownerId(1).build();
        Mockito.when(carRepository.save(any())).thenReturn(audi);
        Car car = carService.saveCar(new Car());
        Assertions.assertEquals(audi, car);
    }

    @Test
    public void getByIdTest() {
        Car bmw = Car.builder()
                .carId(2)
                .brand("bmw")
                .ownerId(2).build();
        Mockito.when(carRepository.findById(anyInt())).thenReturn(Optional.of(bmw));
        Car car = carService.getById(anyInt());
        Assertions.assertEquals(bmw, car);
    }

    @Test
    public void getByIdNotFoundTest() {
        Mockito.when(carRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = Assertions.assertThrows(
                ResponseStatusException.class, () -> carService.getById(anyInt()));
        Assertions.assertEquals("404 NOT_FOUND", responseStatusException.getMessage());
    }

    @Test
    public void getAllCarsTest(){
        List<Car> cars = List.of(
                new Car(1, "mazda", 1),
                new Car(2, "opel", 2));
        Mockito.when(carRepository.findAll()).thenReturn(cars);
        List<Car> usersCars = carService.getAllCars();
        Assertions.assertEquals(cars, usersCars);
    }

    @Test
    public void updateCarTest(){
        Car kamaz = Car.builder()
                .carId(3)
                .brand("kamaz")
                .ownerId(3).build();
        Mockito.when(carRepository.save(any())).thenReturn(kamaz);
        Car car = carService.updateCar(anyInt(), new Car());
        Assertions.assertEquals(kamaz, car);
    }
}
