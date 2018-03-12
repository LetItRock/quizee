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
            String username = "admin@admin.com";
            User oldAdmin = userRepository.findByUsername(username);
            if (oldAdmin == null) {
                System.out.println("CREATING ADMIN USER ----------->" + username);
                User admin = new User();
                admin.setEnabled(true);
                admin.setUsername(username);
                admin.setPassword(encoder.encode("pass"));
                userRepository.save(admin);
            }
        };
    }

}
