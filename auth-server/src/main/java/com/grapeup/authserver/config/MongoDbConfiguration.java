package com.grapeup.authserver.config;

import com.grapeup.authserver.domain.User;
import com.grapeup.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MongoDbConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    CommandLineRunner preLoadMongoDB() {
        return args -> {
            long usersCount = userRepository.count();
            System.out.println("USERS COUNT----------->" + usersCount);
            if (usersCount <= 0) {
                User user = new User();
                user.setEnabled(true);
                user.setUsername("admin@admin.com");
                user.setPassword(encoder.encode("pass"));
                userRepository.save(user);
            }
        };
    }

}
