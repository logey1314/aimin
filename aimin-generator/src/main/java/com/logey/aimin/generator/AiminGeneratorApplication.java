package com.logey.aimin.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AiminGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiminGeneratorApplication.class, args);
    }

}
