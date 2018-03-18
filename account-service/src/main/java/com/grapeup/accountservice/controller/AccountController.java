package com.grapeup.accountservice.controller;

import com.grapeup.accountservice.domain.Account;
import com.grapeup.accountservice.dto.AccountDto;
import com.grapeup.accountservice.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/create")
    public AccountDto create(@Valid @RequestBody AccountDto accountDto) {
        Account newAccount = accountService.create(modelMapper.map(accountDto, Account.class));
        return modelMapper.map(newAccount, AccountDto.class);
    }

    @GetMapping(path = "/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello(Principal principal) {
        return "Secure: hello!";
    }
}
