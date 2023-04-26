package com.depromeet.reunion.server.domain.comment.controller;

import com.depromeet.reunion.server.domain.comment.dto.CommentRequestDto;
import com.depromeet.reunion.server.domain.comment.dto.CommentResponseDto;
import com.depromeet.reunion.server.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }
    @PostMapping("/{memberId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId, @PathVariable("memberId") Long memberId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.createComment(postId, memberId, commentRequestDto));
    }
}
