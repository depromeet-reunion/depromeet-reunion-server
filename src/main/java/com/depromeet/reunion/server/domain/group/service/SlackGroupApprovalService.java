package com.depromeet.reunion.server.domain.group.service;

import com.depromeet.reunion.server.domain.member.model.MemberStatus;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.global.exception.BusinessException;
import com.depromeet.reunion.server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlackGroupApprovalService implements GroupApprovalService {

    private final MemberRepository memberRepository;

    @Override
    public String approveGroup(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_VALID_MEMBER)
        );
        member.setStatus(MemberStatus.APPROVED);
        memberRepository.save(member);
        return memberId + " Approved";
    }

    @Override
    public String rejectGroup(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_VALID_MEMBER)
        );
        member.setStatus(MemberStatus.REJECTED);
        memberRepository.save(member);
        return memberId + "Rejected";
    }
}
