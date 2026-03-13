package com.logey.aimin.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AiminAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiminAdminApplication.class, args);
    }

}
