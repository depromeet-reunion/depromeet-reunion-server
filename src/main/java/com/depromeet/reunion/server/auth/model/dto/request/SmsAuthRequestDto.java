package com.depromeet.reunion.server.auth.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsAuthRequestDto {

    @NotEmpty(message = "Not empty phone number")
    @Pattern(regexp = "^010\\d{8}$", message = "Invalid phone number format - valid format 01012345678")
    private String phoneNumber;

}