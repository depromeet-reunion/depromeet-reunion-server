package com.depromeet.reunion.server.global.security.provider.handler;

import com.depromeet.reunion.server.auth.model.dto.resonse.LoginResponseDto;
import com.depromeet.reunion.server.global.security.provider.SecurityUserDetails;
import com.depromeet.reunion.server.global.security.token.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        String id = String.valueOf(userDetails.getMember().getId());

        String accessToken = jwtTokenProvider.createAccessToken(id);
        String refreshToken = jwtTokenProvider.createRefreshToken(id);
        var loginResult = new LoginResponseDto(accessToken, refreshToken);
        response.setContentType("application/json");
        response.setStatus(HttpStatus.CREATED.value());
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(loginResult));
    }

}


