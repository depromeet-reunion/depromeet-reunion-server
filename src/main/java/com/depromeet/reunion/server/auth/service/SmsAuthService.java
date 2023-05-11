package com.depromeet.reunion.server.auth.service;

import com.depromeet.reunion.server.auth.model.dao.AuthCode;
import com.depromeet.reunion.server.auth.model.dao.AuthResult;
import com.depromeet.reunion.server.auth.model.dto.request.SmsAuthRequestDto;
import com.depromeet.reunion.server.auth.model.dto.request.SmsAuthVerifyRequestDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.AuthVerifyResultDto;
import com.depromeet.reunion.server.auth.model.dto.resonse.SmsAuthSendResponseDto;
import com.depromeet.reunion.server.auth.repository.AuthCodeRepository;
import com.depromeet.reunion.server.auth.repository.AuthResultRepository;
import com.depromeet.reunion.server.auth.utils.AuthCodeProvider;
import com.depromeet.reunion.server.global.exception.BusinessException;
import com.depromeet.reunion.server.global.exception.ErrorCode;
import com.depromeet.reunion.server.infra.ncp.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SmsAuthService {

    private static final int RESEND_LIMIT_SECOND = 150;
    private static final int EXPIRED_TIME = 180;
    private static final int AUTH_EXPIRED_TIME = 43200;
    private final AuthCodeRepository authCodeRepository;
    private final AuthResultRepository authResultRepository;
    private final SmsService smsService;

    public SmsAuthSendResponseDto smsAuthSend(@RequestBody SmsAuthRequestDto smsAuthRequestDto) {

        authCodeRepository.findById(smsAuthRequestDto.getPhoneNumber()).ifPresent(
                authCode -> {
                    if (authCode.getExpiredTime() > RESEND_LIMIT_SECOND) {
                        throw new BusinessException(ErrorCode.AUTH_CODE_RETRY_TIME);
                    }
                }
        );

        var authCode = AuthCodeProvider.createRandomAuthCode();
        var message = createAuthMessage(authCode);

        smsService.sendMessage(smsAuthRequestDto.getPhoneNumber(), message);

        AuthCode authcode = AuthCode.builder()
                .key(smsAuthRequestDto.getPhoneNumber())
                .value(authCode)
                .expiredTime(EXPIRED_TIME).build();

        authCodeRepository.save(authcode);

        return new SmsAuthSendResponseDto("Success", getExpiredTime());
    }

    public AuthVerifyResultDto smsAuthVerify(@RequestBody SmsAuthVerifyRequestDto smsAuthVerifyRequestDto) {
        AuthCode authCode = authCodeRepository.findById(smsAuthVerifyRequestDto.getPhoneNumber()).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_MATCH_AUTH_CODE)
        );
        boolean result = authCode.getValue().equals(smsAuthVerifyRequestDto.getAuthCode());
        if (result) {
            AuthResult authResult = AuthResult.builder().key(authCode.getKey()).value(authCode.getValue()).expiredTime(AUTH_EXPIRED_TIME).build();
            authResultRepository.save(authResult);
        } else {
            throw new BusinessException(ErrorCode.NOT_MATCH_AUTH_CODE);
        }
        return new AuthVerifyResultDto(true);
    }

    private String createAuthMessage(String authCode) {
        return "[DEEPROMEET] 인증번호: " + authCode + " 인증번호를 입력해 주세요. ";
    }

    private LocalDateTime getExpiredTime() {
        return LocalDateTime.now().plusSeconds(SmsAuthService.EXPIRED_TIME);
    }
}
