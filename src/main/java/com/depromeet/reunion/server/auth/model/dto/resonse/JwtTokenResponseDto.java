package com.depromeet.reunion.server.auth.model.dto.resonse;

public record JwtTokenResponseDto(String accessToken, String refreshToken) {
}
