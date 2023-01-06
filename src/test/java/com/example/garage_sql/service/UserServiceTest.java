package com.example.garage_sql.service;

import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.Dao.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private CarRepository carRepository = Mockito.mock(CarRepository.class);
    private UserService userService;

    @Before
    public void init (){
       userService  = new UserService(userRepository, carRepository);
    }
}
