package com.depromeet.reunion.server.domain.member.dto;

import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.member.entity.PartType;
import lombok.*;

@Getter
@Builder
public class MemberResponseDto {
    private Long id;
    private String name;
    private PartType part;
    private String unit;

    // builder 패턴 사용
    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .part(member.getPart())
                .unit(member.getUnit())
                .build();
    }
}
