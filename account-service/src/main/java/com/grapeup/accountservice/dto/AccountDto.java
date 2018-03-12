package com.grapeup.accountservice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AccountDto {
    private String id;
    @NotNull
    @Email
    // TODO validate email
    private String email;
    @NotNull
    @Length(min = 3, max = 30)
    private String name;
    @NotNull
    @Length(min = 2, max = 100)
    private String surname;
    @NotNull
    // TODO validate phone number
    private String phoneNumber;
    private String additionalInfo;
    private LocalDateTime lastSeen = LocalDateTime.now();
}
