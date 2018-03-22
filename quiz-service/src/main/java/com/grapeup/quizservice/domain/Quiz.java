package com.grapeup.quizservice.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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
    @DBRef
    private List<Label> labels;
    @DBRef
    private List<Question> questions;
}
