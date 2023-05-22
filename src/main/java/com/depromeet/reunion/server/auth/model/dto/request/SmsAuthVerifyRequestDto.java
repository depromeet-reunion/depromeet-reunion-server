package com.depromeet.reunion.server.auth.model.dto.request;

import com.depromeet.reunion.server.global.annotation.IsPhone;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SmsAuthVerifyRequestDto {

    @IsPhone
    private String phoneNumber;

    @NotEmpty(message = "Not empty auth code")
    private String authCode;

}
