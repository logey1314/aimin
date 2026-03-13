package com.logey.aimin.drug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AiminDrugApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiminDrugApplication.class, args);
    }

}
