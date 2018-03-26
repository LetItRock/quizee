package com.grapeup.quizservice.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "quizzes")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Quiz extends BaseEntity {
    @Indexed(unique = true) @NotNull @Length(min = 4, max = 64)
    private String name;
    @NotNull @Length(min = 4, max = 300)
    private String shortDescription;
    @NotNull @Length(min = 4, max = 3000)
    private String description;
    private String icon;
    private long duration; // in ms
    private long points;
    private boolean active;
    private boolean showResult;
    private List<String> labels = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();

    public void addLabel(String label) {
        Assert.notNull(label, "Label cannot be null.");
        if(!labels.contains(label)) {
            labels.add(label);
        }
    }

    public void addQuestion(Question question) {
        Assert.notNull(question, "Question cannot be null.");
        if(!questions.contains(question)) {
            questions.add(question);
        }
    }
}
