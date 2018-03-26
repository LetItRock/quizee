package com.grapeup.quizservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionDto {
    private String id;
    private String body;
    private int points;
    private String explanation;
    private boolean isMarkdownEnabled;
    private String type;
    private List<OptionDto> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AnswerDto> answers;
    private List<String> labels;
    private LocalDateTime created;
    private LocalDateTime updated;
}
