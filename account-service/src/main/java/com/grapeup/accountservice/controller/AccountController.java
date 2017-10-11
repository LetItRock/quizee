package com.grapeup.accountservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping(path = "/demo")
    @PreAuthorize("#oauth2.hasScope('ui')")
    public String demo() {
        return "demo";
    }
}
