package com.depromeet.reunion.server.domain.member.dto;

import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.member.entity.PartType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
public class MemberResponseDto {
    @Schema(description = "멤버 id")
    private String id;
    @Schema(description = "멤버 이름")
    private String name;
    @Schema(description = "직군")
    private PartType part;
    @Schema(description = "기수")
    private String unit;

    // builder 패턴 사용
    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .id(String.valueOf(member.getId()))
                .name(member.getName())
                .part(member.getPart())
                .unit(member.getUnit())
                .build();
    }
}
