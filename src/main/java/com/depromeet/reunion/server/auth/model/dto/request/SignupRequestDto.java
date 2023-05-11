package com.depromeet.reunion.server.auth.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @NotEmpty(message = "Not empty name")
    private String name;

    @NotEmpty(message = "Not empty phone number")
    private String phoneNumber;

    @NotEmpty(message = "Not empty password")
    private String password;

    @NotEmpty(message = "Not empty part")
    private String part;

    @NotEmpty(message = "Not empty unit")
    private Integer unit;
}
