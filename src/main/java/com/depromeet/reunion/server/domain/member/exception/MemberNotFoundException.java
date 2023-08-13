package com.depromeet.reunion.server.domain.member.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long memberId) {
        super("Member not found. memberId: " + memberId);
    }
}
