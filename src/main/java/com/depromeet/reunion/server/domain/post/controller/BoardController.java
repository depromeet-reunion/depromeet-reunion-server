package com.depromeet.reunion.server.domain.post.controller;


import com.depromeet.reunion.server.domain.common.ApiResponse;
import com.depromeet.reunion.server.domain.post.dto.response.BoardResponseDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
import com.depromeet.reunion.server.domain.post.service.BoardService;
import com.depromeet.reunion.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return ApiResponse.ok(boardService.getAllBoards());
    }

    // 게시판내 게시물 모두 조회
    @GetMapping("/{boardId}/posts")
    public ResponseEntity<List<PostListResponseDto>> getPostsByBoard(@PathVariable("boardId") Long boardId) {
        return ApiResponse.ok(postService.getPostsByBoard(boardId));
    }

    // 게시글 작성
    @PostMapping("/{boardId}/posts/{memberId}")
    public ResponseEntity<PostResponseDto> createPost(@PathVariable("boardId") Long boardId, @PathVariable("memberId") Long memberId,
                                                      @RequestPart(value = "postRequest") PostRequestDto postRequestDto,
                                                      @RequestPart(value = "imageFiles",required = false) List<MultipartFile> multipartFiles) {
        return ApiResponse.created(postService.createPost(boardId, memberId, postRequestDto, multipartFiles));
    }
}
