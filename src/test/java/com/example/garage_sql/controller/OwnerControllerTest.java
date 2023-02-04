package com.example.garage_sql.controller;

import com.example.garage_sql.model.Car;
import com.example.garage_sql.model.Owner;
import com.example.garage_sql.repository.Dao.CarRepository;
import com.example.garage_sql.repository.Dao.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void getUserTest() throws Exception{
        Owner dima = userRepository.save(Owner.builder().name("Dima").surname("Storozhenko").build());
        mockMvc.perform(get("/users/{id}", dima.getUserId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUserNotFoundTest() throws Exception{
        Owner rima = userRepository.save(Owner.builder().name("Rima").surname("Sokolova").build());
        mockMvc.perform(get("/users/{id}", 5))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void getAllUserTest() throws Exception{
        Owner lina = userRepository.save(Owner.builder().name("Lina").surname("Kozlova").build());
        Owner vova = userRepository.save(Owner.builder().name("Vova").surname("Ivanov").build());
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getUserCarsTest() throws Exception{
        Owner kiril = userRepository.save(Owner.builder().name("Kiril").surname("Kozov").build());
        Car opel = carRepository.save(Car.builder().brand("opel").ownerId(kiril.getUserId()).build());
        Car mazda = carRepository.save(Car.builder().brand("mazda").ownerId(kiril.getUserId()).build());
        mockMvc.perform(get("/users/{id}/cars", kiril.getUserId())).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void saveUserTest() throws Exception{
        Owner vitalik = userRepository.save(Owner.builder().name("Vitalik").surname("Roi").build());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(asJsonString(vitalik))
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
    public void saveCarUserTest() throws Exception{
        Owner oleg = userRepository.save(Owner.builder().name("Oleg").surname("Popkin").build());
        Car niva = carRepository.save(Car.builder().brand("niva").ownerId(oleg.getUserId()).build());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{id}/car", oleg.getUserId())
                        .content(asJsonString(niva))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserTest() throws Exception{
        Owner igor = userRepository.save(Owner.builder().name("Igor").surname("Kononov").build());
        Owner updateUser = Owner.builder().name("Kosty").surname("Kononov").build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{id}", igor.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateUser)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Kosty"))
                .andDo(print());
    }

    @Test
    public void deleteUserTest() throws Exception{
        Owner nina = userRepository.save(Owner.builder().name("Nina").surname("Pavelchuk").build());
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", nina.getUserId()))
                .andExpect(status().isOk());
    }
}
