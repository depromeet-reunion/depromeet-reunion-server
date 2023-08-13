package com.depromeet.reunion.server.auth.controller;

import com.depromeet.reunion.server.auth.model.dto.request.LoginRequestDto;
import com.depromeet.reunion.server.auth.model.dto.request.RefreshTokenRequestDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.JwtTokenResponseDto;
import com.depromeet.reunion.server.auth.service.AuthService;
import com.depromeet.reunion.server.domain.member.model.dto.request.SignupRequestDto;
import com.depromeet.reunion.server.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Controller", description = "인증 API")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;

    @Operation(summary = "토큰 재발급", description = "refreshToken 을 입력하고 accessToken, refreshToken 을 받습니다.")
    @PostMapping("/reissue")
    public ResponseEntity<JwtTokenResponseDto> reissue(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) throws Exception {
        var response = authService.reissue(refreshTokenRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원가입", description = "이름, 전화번호 등을 입력하고 accessToken, refreshToken 을 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = JwtTokenResponseDto.class))),
    })
    @PostMapping("/sign-up")
    public ResponseEntity<JwtTokenResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        var response = memberService.signUp(signupRequestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * swagger 에 보여주기 위한 메서드.
     */
    @Operation(summary = "로그인", description = "ID, PASSWORD 를 입력하고 accessToken, refreshToken 을 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = JwtTokenResponseDto.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        // Do Nothing
        return ResponseEntity.ok(null);
    }

}
