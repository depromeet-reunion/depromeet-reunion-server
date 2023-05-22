package com.depromeet.reunion.server.auth.model.dto.request;

import com.depromeet.reunion.server.global.annotation.IsPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsAuthRequestDto {

    @IsPhone
    private String phoneNumber;

}