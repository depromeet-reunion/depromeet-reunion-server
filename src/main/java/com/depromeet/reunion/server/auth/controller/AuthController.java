package com.depromeet.reunion.server.auth.controller;

import com.depromeet.reunion.server.auth.model.dto.request.RefreshTokenRequestDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.JwtTokenResponseDto;
import com.depromeet.reunion.server.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reissue")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping
    public ResponseEntity<JwtTokenResponseDto> reissue(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) throws Exception {
        var response = authService.reissue(refreshTokenRequestDto);
        return ResponseEntity.ok(response);
    }
}
