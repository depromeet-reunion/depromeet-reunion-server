package com.depromeet.reunion.server.auth.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SmsAuthVerifyRequestDto {

    @NotEmpty(message = "Not empty phone number")
    @Pattern(regexp = "^010\\d{8}$", message = "Invalid phone number format - valid format 01012345678")
    private String phoneNumber;

    @NotEmpty(message = "Not empty auth code")
    private String authCode;

}
