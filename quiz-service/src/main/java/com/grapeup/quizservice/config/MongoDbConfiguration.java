package com.grapeup.quizservice.config;

import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class MongoDbConfiguration {

    @Autowired
    private QuizRepository quizRepository;

    @Bean
    public CommandLineRunner fillDatabase() {
        return strings -> {
            long count = quizRepository.count();
            String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Donec pharetra rhoncus mauris non tristique. " +
                    "Etiam a eros convallis, pulvinar nisi in, accumsan tellus. " +
                    "Nulla rhoncus mi quam, id molestie quam consectetur sed. " +
                    "Fusce vel tortor efficitur, malesuada velit vel, eleifend tellus. " +
                    "Suspendisse urna est, faucibus eget luctus at, varius eget elit. " +
                    "Sed consectetur magna nulla, ac sagittis leo blandit vel. " +
                    "Nam fringilla dui ut arcu consectetur luctus. " +
                    "Duis id mollis nulla. " +
                    "Suspendisse auctor leo vel orci facilisis, ac molestie justo facilisis. ";
            if (count <= 0) {
                for (int i = 0; i < 10; i++) {
                    String uuid = UUID.randomUUID().toString();
                    Quiz quiz = Quiz.builder()
                            .name(uuid)
                            .shortDescription("Short description " + uuid)
                            .description("Description " + lorem + uuid)
                            .icon("")
                            .duration(1800000) // 30 min
                            .points(ThreadLocalRandom.current().nextInt(1, 10 + 1))
                            .active(true)
                            .showResult(true)
                            .build();
                    quizRepository.save(quiz);
                }
            }
        };
    }

}
