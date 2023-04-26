package com.depromeet.reunion.server.domain.post.controller;

import com.depromeet.reunion.server.domain.post.dto.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.PostResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.depromeet.reunion.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    final private PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @PutMapping("/{postId}")
    public void updatePost(@PathVariable("postId") Long postId, @RequestBody PostRequestDto postSaveDto) {
        postService.updatePost(postId, postSaveDto);
    }
    @PostMapping("/{postId}/like/{memberId}")
    public void likePost(@PathVariable("postId") Long postId, @PathVariable("memberId") Long memberId) {
        postService.likePost(postId, memberId);
    }
}
