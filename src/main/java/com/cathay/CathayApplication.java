package com.cathay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CathayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CathayApplication.class, args);
    }

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }