package com.grapeup.quizservice.domain;

import com.grapeup.quizservice.domain.pojo.Answer;
import com.grapeup.quizservice.domain.pojo.Option;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

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
    @NotNull @Length(min = 4, max = 3000)
    private String body;
    @NotNull
    private int points;
    @Length(min = 4, max = 3000)
    private String explanation;
    private boolean isMarkdownEnabled;
    @NotNull
    private Type type = Type.SINGLE;
    @NotNull @NotEmpty
    private List<Option> options = new ArrayList<>();
    @NotNull @NotEmpty
    private List<Answer> answers = new ArrayList<>();
    @DBRef
    private List<Label> labels = new ArrayList<>();

    public void addLabel(Label label) {
        Assert.notNull(label, "Label cannot be null.");
        if(!labels.contains(label)) {
            labels.add(label);
        }
    }

    public enum Type {
        SINGLE, MULTIPLE, ORDERED, MATCH, BOOLEAN, TEXT
    }
}
