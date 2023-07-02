package com.depromeet.reunion.server.auth.model.dto.request;

import com.depromeet.reunion.server.global.annotation.IsPhone;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @IsPhone
    private String phoneNumber;

    private String password;
}
