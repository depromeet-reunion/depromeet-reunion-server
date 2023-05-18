package com.depromeet.reunion.server.domain.member.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^010\\d{8}$", message = "Invalid phone number format - valid format 01012345678")
    private String phoneNumber;

    @NotEmpty(message = "Not empty password")
    private String password;

    @NotEmpty(message = "Not empty part")
    private String part;

    @NotEmpty(message = "Not empty unit")
    private Integer unit;
}
