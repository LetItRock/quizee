package com.grapeup.accountservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "accounts")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String additionalInfo;
    private LocalDateTime lastSeen = LocalDateTime.now();
}
