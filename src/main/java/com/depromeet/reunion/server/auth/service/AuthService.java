package com.depromeet.reunion.server.auth.service;

import com.depromeet.reunion.server.auth.model.dto.request.RefreshTokenRequestDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.JwtTokenResponseDto;
import com.depromeet.reunion.server.global.security.token.JwtTokenProvider;
import com.depromeet.reunion.server.global.security.token.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenResponseDto reissue(RefreshTokenRequestDto refreshTokenRequestDto) throws Exception {
        String refreshToken = refreshTokenRequestDto.getRefreshToken();
        String userId = jwtTokenUtil.getId(refreshToken);
        String accessToken = jwtTokenProvider.createAccessToken(userId);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId);
        return new JwtTokenResponseDto(accessToken, newRefreshToken);
    }
}
