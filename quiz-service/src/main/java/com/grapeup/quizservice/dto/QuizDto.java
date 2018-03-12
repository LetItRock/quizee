package com.grapeup.quizservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuizDto {
    private String id;
    private String name;
    private String shortDescription;
    private String description;
    private String icon;
    private long duration; // in ms
    private long points;
    private boolean active;
    private boolean showResult;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();
}
