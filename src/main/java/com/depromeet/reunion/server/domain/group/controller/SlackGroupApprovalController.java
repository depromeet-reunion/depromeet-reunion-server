package com.depromeet.reunion.server.domain.group.controller;

import com.depromeet.reunion.server.domain.group.service.GroupApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/slack/group")
@RequiredArgsConstructor
public class SlackGroupApprovalController {

    private final GroupApprovalService groupApprovalService;

    @GetMapping("/approve/{memberId}")
    public void approveToGroup(
            @PathVariable long memberId
    ) {
        groupApprovalService.approveGroup(memberId);
    }

    @GetMapping("/reject/{memberId}")
    public void rejectToGroup(
            @PathVariable long memberId
    ) {
        groupApprovalService.rejectGroup(memberId);
    }

}
