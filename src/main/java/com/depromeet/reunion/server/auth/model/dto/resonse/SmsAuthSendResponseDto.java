package com.depromeet.reunion.server.auth.model.dto.resonse;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SmsAuthSendResponseDto {
    private final String status;
    private final LocalDateTime expiredAt;
}
