package com.depromeet.reunion.server.domain.comment.controller;

import com.depromeet.reunion.server.domain.comment.dto.request.CommentRequestDto;
import com.depromeet.reunion.server.domain.comment.dto.response.CommentResponseDto;
import com.depromeet.reunion.server.domain.comment.service.CommentService;
import com.depromeet.reunion.server.domain.common.ResponseDto;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.global.annotation.ReqMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Comment Controller", description = "댓글 API")
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "댓글 목록 조회", description = "게시글에 달린 댓글 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommentResponseDto.class)))),

    })
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable("postId") Long postId) {
        return ResponseDto.ok(commentService.getCommentsByPost(postId));
    }

    @Operation(summary = "댓글 생성", description = "게시글에 댓글을 작성합니다. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
    })
    @PostMapping()
    public ResponseEntity<CommentResponseDto> createComment(@ReqMember Member member, @PathVariable("postId") Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseDto.created(commentService.createComment(postId, member.getId(), commentRequestDto));
    }
}
