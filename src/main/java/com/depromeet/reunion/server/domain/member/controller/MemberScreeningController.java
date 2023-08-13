package com.depromeet.reunion.server.domain.member.controller;

import com.depromeet.reunion.server.domain.member.service.MemberScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members/{memberId}")
@RequiredArgsConstructor
public class MemberScreeningController {
    private final MemberScreeningService memberScreeningService;

    @PostMapping("/approve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@PathVariable Long memberId) {
        memberScreeningService.approve(memberId);
    }

    @PostMapping("/reject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reject(@PathVariable Long memberId) {
        memberScreeningService.reject(memberId);
    }
}
