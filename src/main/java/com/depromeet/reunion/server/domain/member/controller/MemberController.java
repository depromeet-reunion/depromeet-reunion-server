package com.depromeet.reunion.server.domain.member.controller;

import com.depromeet.reunion.server.domain.post.dto.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {
    private final PostService postService;
    @GetMapping("/posts/me/{memberId}")
    public ResponseEntity<List<PostListResponseDto>> getMyPosts(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(postService.getMyPosts(memberId));
    }
}
