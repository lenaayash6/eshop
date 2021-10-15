package com.example.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class EshopApplication {

    public static void main(String[] args) {

        SpringApplication.run(EshopApplication.class, args);
    }

}
