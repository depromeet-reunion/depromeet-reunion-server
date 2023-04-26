package com.depromeet.reunion.server.domain.member.dto;

import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.member.entity.PartType;
import lombok.*;

@Getter
@Builder
public class MemberSimpleResponseDto {
    private Long id;
    private String name;
    private PartType part;
    private String unit;

//    public MemberSimpleResponseDto(Member member) {
//        this.id = member.getId();
//        this.name = member.getName();
//        this.part = member.getPart();
//        this.unit = member.getUnit();
//    }

    // builder 패턴 사용
    public static MemberSimpleResponseDto of (Member member) {
        return MemberSimpleResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .part(member.getPart())
                .unit(member.getUnit())
                .build();
    }
}
