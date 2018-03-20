package com.grapeup.quizservice.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "quizzes")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Quiz {
    @Id
    private String id;
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
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();
}
