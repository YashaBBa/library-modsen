package com.modsen.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DiaryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiaryServiceApplication.class,args);
    }
}