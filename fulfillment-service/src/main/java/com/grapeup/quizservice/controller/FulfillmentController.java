package com.grapeup.quizservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FulfillmentController {

    @GetMapping("/test")
    @PreAuthorize("#oauth2.hasScope('ui') and hasRole('ADMIN')")
    public String hello() {
        return "Hello!";
    }
}
