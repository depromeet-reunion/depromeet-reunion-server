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

    /**
     * This method send sms auth code to user's phone number.
     * <p> If auth code is already exist, then check expired time.
     * <p> Prevent send message if EXPIRED_TIME seconds have not passed since the previous request.
     * <p> Create Random Auth Code and send message to smsAuthRequestDto phone number.
     * <p> Save auth code to redis - add expired time to EXPIRED_TIME seconds.
     *
     * @param smsAuthRequestDto phone number
     **/
    public SmsAuthSendResponseDto smsAuthSend(SmsAuthRequestDto smsAuthRequestDto) {
        String phoneNumber = smsAuthRequestDto.getPhoneNumber();

        authCodeRepository.findById(phoneNumber).ifPresent(
                authCode -> {
                    if (authCode.getExpiredTime() > RESEND_LIMIT_SECOND) {
                        throw new BusinessException(ErrorCode.AUTH_CODE_RETRY_TIME);
                    }
                }
        );

        var authCode = AuthCodeProvider.createRandomAuthCode();
        var message = createAuthMessage(authCode);

        smsService.sendMessage(phoneNumber, message);

        AuthCode authcode = AuthCode.builder()
                .key(phoneNumber)
                .value(authCode)
                .expiredTime(EXPIRED_TIME).build();

        authCodeRepository.save(authcode);

        return new SmsAuthSendResponseDto("Success", getExpiredTime());
    }

    /**
     * This Method check auth code by user's phone number.
     * <p> If auth code is not exist, then throw NOT_MATCH_AUTH_CODE exception.
     * <p> If auth code is exist, then check auth code is same.
     *
     * @param smsAuthVerifyRequestDto phone number, auth code
     * @return AuthVerifyResultDto
     */
    public AuthVerifyResultDto smsAuthVerify(SmsAuthVerifyRequestDto smsAuthVerifyRequestDto) {
        AuthCode authCode = authCodeRepository.findById(smsAuthVerifyRequestDto.getPhoneNumber())
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTH_CODE_SEND_REQUIRED));

        if (authCode.getValue().equals(smsAuthVerifyRequestDto.getAuthCode())) {
            AuthResult authResult = AuthResult.builder().key(authCode.getKey()).value(authCode.getValue()).expiredTime(AUTH_EXPIRED_TIME).build();
            authResultRepository.save(authResult);
            return new AuthVerifyResultDto(true);
        } else {
            return new AuthVerifyResultDto(false);
        }
    }

    /**
     * This method create auth message.
     *
     * @param authCode auth code
     * @return String
     */
    private String createAuthMessage(String authCode) {
        return "[DEEPROMEET] 인증번호: " + authCode + " 인증번호를 입력해 주세요. ";
    }


    /**
     * This method return expired time.
     *
     * @return LocalDateTime
     */
    private LocalDateTime getExpiredTime() {
        return LocalDateTime.now().plusSeconds(SmsAuthService.EXPIRED_TIME);
    }
}
