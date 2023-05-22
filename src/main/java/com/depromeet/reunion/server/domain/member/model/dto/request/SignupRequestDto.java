package com.depromeet.reunion.server.domain.member.model.dto.request;

import com.depromeet.reunion.server.global.annotation.IsPhone;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @NotEmpty(message = "Not empty name")
    private String name;

    @IsPhone
    private String phoneNumber;

    @NotEmpty(message = "Not empty password")
    private String password;

    @NotEmpty(message = "Not empty part")
    private String part;

    @NotEmpty(message = "Not empty unit")
    private Integer unit;
}
