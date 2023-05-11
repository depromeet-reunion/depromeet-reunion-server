package com.depromeet.reunion.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C-0000", "Bad Request"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C-0001", "Not Found the Contents"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-0002", "Internal Server Error"),

    AUTH_CODE_RETRY_TIME(HttpStatus.BAD_REQUEST, "C-0003", "Auth Code Retry Time Exceeded"),
    NOT_MATCH_AUTH_CODE(HttpStatus.BAD_REQUEST, "C-0003", "Not Match Auth Code"),

    // Group
    NOT_EXIST_PART_OR_UNIT(HttpStatus.BAD_REQUEST, "G-0001", "Not Exist Part Or Unit"),


    // User
    WAITING_MEMBER_ERROR(HttpStatus.BAD_REQUEST, "U-0001", "Waiting Member"),
    REJECTED_MEMBER_ERROR(HttpStatus.BAD_REQUEST, "U-0002", "Rejected Member"),
    // Auth
    UNAUTHORIZED_PHONE_NUMBER(HttpStatus.UNAUTHORIZED, "AUTH-0001", "Unauthorized Phone Number"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN-0003", "Invalid token"),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "AUTH-0002", "Already Exist User"),
    PHONE_NUMBER_OR_PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "AUTH-0003", "Phone Number Or Password Not Matched"),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
