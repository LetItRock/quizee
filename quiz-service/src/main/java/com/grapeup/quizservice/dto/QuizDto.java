package com.grapeup.quizservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuizDto {
    private String id;
    private boolean active;
    private String name;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();
}
