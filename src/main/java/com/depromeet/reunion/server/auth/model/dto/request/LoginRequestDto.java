package com.depromeet.reunion.server.auth.model.dto.request;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    private String phoneNumber;
    private String password;
}
