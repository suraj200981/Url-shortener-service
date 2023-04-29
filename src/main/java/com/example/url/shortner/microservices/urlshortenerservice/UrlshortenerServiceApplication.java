package com.example.url.shortner.microservices.urlshortenerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.persistence.Entity;

@SpringBootApplication
@EnableFeignClients
public class UrlshortenerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerServiceApplication.class, args);
    }

}
