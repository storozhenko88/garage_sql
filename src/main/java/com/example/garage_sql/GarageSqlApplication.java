package com.example.garage_sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GarageSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarageSqlApplication.class, args);
    }

}
