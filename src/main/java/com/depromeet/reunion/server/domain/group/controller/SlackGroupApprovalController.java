package com.depromeet.reunion.server.domain.group.controller;

import com.depromeet.reunion.server.domain.group.service.GroupApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> approveToGroup(
            @PathVariable long memberId
    ) {
        var response = groupApprovalService.approveGroup(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reject/{memberId}")
    public ResponseEntity<String> rejectToGroup(
            @PathVariable long memberId
    ) {
        var response = groupApprovalService.rejectGroup(memberId);
        return ResponseEntity.ok(response);
    }

}
