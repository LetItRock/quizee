package com.grapeup.accountservice.service;

import com.grapeup.accountservice.client.AuthServerClient;
import com.grapeup.accountservice.domain.Account;
import com.grapeup.accountservice.domain.User;
import com.grapeup.accountservice.repository.AccountRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthServerClient authServerClient;

    @Override
    public Account create(Account account) {
        account.setId(null);
        validateAccount(account);

        String password = RandomStringUtils.randomAlphanumeric(8);
        User user = User
                .builder()
                .username(account.getEmail())
                .password(password)
                .build();
        authServerClient.createUser(user);

        // TODO send email with login and password

        return accountRepository.save(account);
    }

    private void validateAccount(Account account) {
        String email = account.getEmail();
        Account oldAccount = accountRepository.findByEmail(email);
        Assert.isNull(oldAccount, "Account already exist: " + email);
    }
}
