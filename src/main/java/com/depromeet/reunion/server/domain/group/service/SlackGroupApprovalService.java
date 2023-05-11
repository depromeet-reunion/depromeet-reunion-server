package com.depromeet.reunion.server.domain.group.service;

import com.depromeet.reunion.server.domain.member.model.Member;
import com.depromeet.reunion.server.domain.member.model.MemberStatus;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlackGroupApprovalService implements GroupApprovalService {

    private final MemberRepository memberRepository;

    @Override
    public void approveGroup(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 멤버가 없습니다.")
        );
        member.setStatus(MemberStatus.APPROVED);
        memberRepository.save(member);
    }

    @Override
    public void rejectGroup(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 멤버가 없습니다.")
        );
        member.setStatus(MemberStatus.REJECTED);
        memberRepository.save(member);
    }
}
