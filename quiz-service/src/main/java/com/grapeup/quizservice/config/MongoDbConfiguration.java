package com.grapeup.quizservice.config;

import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MongoDbConfiguration {

    @Autowired
    private QuizRepository quizRepository;

    @Bean
    public CommandLineRunner fillDatabase() {
        return strings -> {
            long count = quizRepository.count();
            if (count <= 0) {
                for (int i = 0; i < 10; i++) {
                    Quiz quiz = Quiz.builder()
                            .active(true)
                            .name(UUID.randomUUID().toString())
                            .build();
                    quizRepository.save(quiz);
                }
            }
        };
    }

}
