package com.depromeet.reunion.server.domain.member.controller;

import com.depromeet.reunion.server.auth.model.dto.resonse.JwtTokenResponseDto;
import com.depromeet.reunion.server.domain.member.model.dto.request.SignupRequestDto;
import com.depromeet.reunion.server.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SignUpController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<JwtTokenResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        var response = memberService.signUp(signupRequestDto);
        return ResponseEntity.ok(response);
    }

}
