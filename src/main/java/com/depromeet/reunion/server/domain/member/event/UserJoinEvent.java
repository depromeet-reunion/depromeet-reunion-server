package com.depromeet.reunion.server.domain.member.event;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class UserJoinEvent {
    private Member member;
}
