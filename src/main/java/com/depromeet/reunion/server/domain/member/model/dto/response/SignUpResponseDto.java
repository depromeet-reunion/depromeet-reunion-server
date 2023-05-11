package com.depromeet.reunion.server.domain.member.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private String accessToken;
    private String refreshToken;

}
