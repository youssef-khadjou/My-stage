package com.monstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MonStageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonStageApplication.class, args);
    }
}