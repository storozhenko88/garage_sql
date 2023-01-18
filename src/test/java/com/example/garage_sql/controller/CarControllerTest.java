package com.example.garage_sql.controller;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void getByIdTest() throws Exception {
        Car audi = carRepository.save(Car.builder().brand("audi").ownerId(3).build());
        mockMvc.perform(get("/cars/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        Car tesla = carRepository.save(Car.builder().brand("tesla").ownerId(3).build());
        mockMvc.perform(get("/cars/{id}", 100))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void saveCarTest() throws Exception {
        Car niva = carRepository.save(Car.builder().brand("niva").ownerId(3).build());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cars")
                        .content(asJsonString(niva))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllCarsTest() throws Exception {
        Car opel = carRepository.save(Car.builder().brand("opel").ownerId(2).build());
        Car mazda = carRepository.save(Car.builder().brand("mazda").ownerId(4).build());
        mockMvc.perform(get("/cars")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void updateCarTest() throws Exception {
        Car lexus = carRepository.save(Car.builder().brand("lexus").ownerId(7).build());
        Car carUpdate = Car.builder().brand("lanos").ownerId(7).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/cars/{id}", lexus.getCarId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("lanos"))
                .andDo(print());
    }

    @Test
    public void deleteCarTest() throws Exception {
        Car toyota = carRepository.save(Car.builder().brand("toyota").ownerId(8).build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/{id}", toyota.getCarId()))
                .andExpect(status().isOk());
    }
}
