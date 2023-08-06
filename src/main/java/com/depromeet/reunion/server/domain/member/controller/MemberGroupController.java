package com.depromeet.reunion.server.domain.member.controller;

import com.depromeet.reunion.server.domain.member.service.MemberGroupService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member-groups")
@RequiredArgsConstructor
public class MemberGroupController {

    private final MemberGroupService memberGroupService;

    @GetMapping("/parts")
    public ResponseEntity<List<String>> getAllPart() {
        var response = memberGroupService.getAllPart();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/units")
    public ResponseEntity<List<Long>> getAllUnit() {
        var response = memberGroupService.getAllUnit();
        return ResponseEntity.ok(response);
    }


}
