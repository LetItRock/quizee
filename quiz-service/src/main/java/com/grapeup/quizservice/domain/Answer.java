package com.grapeup.quizservice.domain;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Answer {
    private int order;
    private String stringAnswer;
    private int integerAnswer;
    private boolean booleanAnswer;
}
