package com.grapeup.quizservice.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "labels")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Label extends BaseEntity{
    @Indexed(unique = true) @NotNull @Length(min = 4, max = 64)
    private String name;
}
