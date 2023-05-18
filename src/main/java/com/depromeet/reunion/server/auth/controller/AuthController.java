package com.depromeet.reunion.server.auth.controller;

import com.depromeet.reunion.server.auth.model.dto.request.SmsAuthRequestDto;
import com.depromeet.reunion.server.auth.model.dto.request.SmsAuthVerifyRequestDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.AuthVerifyResultDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.SmsAuthSendResponseDto;
import com.depromeet.reunion.server.auth.service.SmsAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SmsAuthService smsAuthService;

    @PostMapping("/sms/send")
    @ResponseBody
    public ResponseEntity<SmsAuthSendResponseDto> smsAuth(@RequestBody @Valid SmsAuthRequestDto smsAuthRequestDto) {
        var response = smsAuthService.smsAuthSend(smsAuthRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sms/verify")
    @ResponseBody
    public ResponseEntity<AuthVerifyResultDto> smsAuthVerify(@RequestBody @Valid SmsAuthVerifyRequestDto smsAuthVerifyRequestDto) {
        var response = smsAuthService.smsAuthVerify(smsAuthVerifyRequestDto);
        return ResponseEntity.ok(response);
    }

}
