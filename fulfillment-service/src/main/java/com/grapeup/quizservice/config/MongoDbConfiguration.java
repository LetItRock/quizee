package com.grapeup.quizservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConfiguration {

    @Bean
    public CommandLineRunner fillDatabase() {
        return strings -> {
            // TODO fill db data
        };
    }
}
