package com.grapeup.quizservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String name;
    private String shortDescription;
    private String description;
    private String icon;
    private long duration; // in ms
    private long points;
    private boolean active;
    private boolean showResult;
    private List<LabelDto> labels;
    private List<QuestionDto> questions;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();
}
