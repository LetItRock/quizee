package com.grapeup.accountservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping
    @PreAuthorize("#oauth2.hasScope('browser')")
    public String demo() {
        return "demo";
    }
}
