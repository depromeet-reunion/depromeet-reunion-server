package com.depromeet.reunion.server.domain.member.service;

import com.depromeet.reunion.server.auth.model.dto.resonse.JwtTokenResponseDto;
import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.member.event.UserJoinEvent;
import com.depromeet.reunion.server.domain.member.model.dto.request.SignupRequestDto;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.model.entity.MemberAuth;
import com.depromeet.reunion.server.domain.member.model.entity.MemberGroup;
import com.depromeet.reunion.server.domain.member.model.entity.MemberStatus;
import com.depromeet.reunion.server.domain.member.repository.MemberAuthRepository;
import com.depromeet.reunion.server.domain.member.repository.MemberGroupRepository;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.global.exception.BusinessException;
import com.depromeet.reunion.server.global.exception.ErrorCode;
import com.depromeet.reunion.server.global.security.token.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberAuthRepository memberAuthRepository;
    private final MemberGroupRepository memberGroupRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public JwtTokenResponseDto signUp(SignupRequestDto signupRequestDto) {
        memberRepository.findByPhoneNumber(signupRequestDto.getPhoneNumber())
                .ifPresent(
                        member -> {
                            throw new BusinessException(ErrorCode.ALREADY_EXIST_USER);
                        }
                );

        MemberAuth memberAuth = MemberAuth.builder()
                .phoneNumber(signupRequestDto.getPhoneNumber())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build();

        memberAuthRepository.save(memberAuth);

        MemberGroup memberGroup = memberGroupRepository
                .findMemberGroupByPartAndUnit(signupRequestDto.getPart(), signupRequestDto.getUnit())
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.NOT_EXIST_PART_OR_UNIT)
                );

        memberGroupRepository.save(memberGroup);

        Member member = Member.builder()
                .name(signupRequestDto.getName())
                .status(MemberStatus.READY)
                .memberAuth(memberAuth)
                .memberGroup(memberGroup)
                .build();

        memberRepository.save(member);

        var event = new UserJoinEvent(member);
        eventPublisher.publishEvent(event);

        String accessToken = jwtTokenProvider.createAccessToken(member.getId().toString());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId().toString());

        return new JwtTokenResponseDto(accessToken, refreshToken);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_VALID_MEMBER));
        return MemberResponseDto.fromEntity(member);
    }
}
