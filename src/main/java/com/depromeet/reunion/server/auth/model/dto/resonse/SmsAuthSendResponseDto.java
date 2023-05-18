package com.depromeet.reunion.server.auth.model.dto.resonse;

import java.time.LocalDateTime;

public record SmsAuthSendResponseDto(String status, LocalDateTime expiredAt) {
}
