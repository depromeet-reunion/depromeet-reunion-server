package com.depromeet.reunion.server.auth.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsAuthVerifyRequestDto {

    @NotEmpty(message = "Not empty phone number")
    private String phoneNumber;

    @NotEmpty(message = "Not empty auth code")
    private String authCode;
}
