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
            long accountsCount = accountRepository.count();
            if (accountsCount <= 0) {
                Account adminAccount = new Account();
                adminAccount.setEmail("paty@grapeup.com");
                adminAccount.setName("Pavlo");
                adminAccount.setSurname("Tymchuk");
                adminAccount.setPhoneNumber("121212121");
                accountRepository.save(adminAccount);
            }
        };
    }

}
