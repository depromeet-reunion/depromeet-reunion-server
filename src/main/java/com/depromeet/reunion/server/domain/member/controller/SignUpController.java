package com.depromeet.reunion.server.domain.member.controller;

import com.depromeet.reunion.server.auth.model.dto.resonse.JwtTokenResponseDto;
import com.depromeet.reunion.server.domain.member.model.dto.request.SignupRequestDto;
import com.depromeet.reunion.server.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SignUp Controller", description = "회원가입 API")
@RestController
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<JwtTokenResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        var response = memberService.signUp(signupRequestDto);
        return ResponseEntity.ok(response);
    }

}
