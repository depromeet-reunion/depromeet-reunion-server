package com.depromeet.reunion.server.domain.post.controller;


import com.depromeet.reunion.server.domain.post.dto.BoardResponseDto;
import com.depromeet.reunion.server.domain.post.dto.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.PostResponseDto;
import com.depromeet.reunion.server.domain.post.service.BoardService;
import com.depromeet.reunion.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    // 게시판 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    // 게시판내 게시물 모두 조회
    @GetMapping("/{boardId}/posts")
    public ResponseEntity<List<PostListResponseDto>> getPostsByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(postService.getPostsByBoard(boardId));
    }

    // 게시글 작성
    @PostMapping("/{boardId}/posts/{memberId}")
    public ResponseEntity<PostResponseDto> createPost(@PathVariable("boardId") Long boardId, @PathVariable("memberId") Long memberId, @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.createPost(boardId, memberId, postRequestDto));
    }
}
