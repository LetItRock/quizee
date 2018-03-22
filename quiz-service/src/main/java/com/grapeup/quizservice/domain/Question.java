package com.grapeup.quizservice.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "questions")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseEntity {
    @NotNull @Length(min = 4, max = 1000)
    private String body;
    @NotNull
    private int points;
    @NotNull
    private Type type = Type.SINGLE;
    private List<String> options = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();
    @DBRef
    private List<Label> labels;

    public enum Type {
        SINGLE, MULTIPLE, ORDERED, MATCH, BOOLEAN, TEXT
    }
}
