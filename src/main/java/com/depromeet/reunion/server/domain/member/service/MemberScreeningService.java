package com.depromeet.reunion.server.domain.member.service;

import com.depromeet.reunion.server.domain.member.exception.MemberNotFoundException;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberScreeningService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입 승인
     */
    public void approve(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        member.setScreeningStatusApproved();
    }

    /**
     * 회원가입 거절
     */
    public void reject(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        member.setScreeningStatusRejected();
    }
}
