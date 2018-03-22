package com.grapeup.quizservice.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

abstract class BaseEntity {
    @Id
    private String id;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();
}
