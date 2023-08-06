package com.depromeet.reunion.server.domain.member.dto;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
    private Long id;
    private String name;
    private String part;
    private Integer unit;

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .part(member.getMemberGroup().getPart())
                .unit(member.getMemberGroup().getUnit())
                .build();
    }
}
