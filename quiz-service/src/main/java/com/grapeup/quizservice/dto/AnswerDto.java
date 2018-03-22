package com.grapeup.quizservice.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private int order;
    private String stringAnswer;
    private int integerAnswer;
    private boolean booleanAnswer;
}
