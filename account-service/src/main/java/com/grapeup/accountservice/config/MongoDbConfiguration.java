package com.grapeup.accountservice.config;

import com.grapeup.accountservice.domain.Account;
import com.grapeup.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConfiguration {

    @Autowired
    private AccountRepository accountRepository;

    @Bean
    CommandLineRunner preLoadMongoDB() {
        return args -> {
            String email = "admin@admin.com";
            Account adminAccount = accountRepository.findByEmail(email);
            if (adminAccount == null) {
                Account admin =
                        Account
                                .builder()
                                .id("1")
                                .email(email)
                                .name("Pavlo")
                                .surname("Tymchuk")
                                .phoneNumber("121212121")
                                .additionalInfo("From AGHacks")
                                .build();
                accountRepository.save(admin);
            }
        };
    }

}
