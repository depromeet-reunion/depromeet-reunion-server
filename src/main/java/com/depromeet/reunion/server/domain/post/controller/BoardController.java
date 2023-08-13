package com.depromeet.reunion.server.domain.post.controller;

import com.depromeet.reunion.server.domain.common.ResponseDto;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.post.dto.request.BoardRequestDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.BoardResponseDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
import com.depromeet.reunion.server.domain.post.service.BoardService;
import com.depromeet.reunion.server.domain.post.service.PostService;
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

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "Board Controller", description = "게시판 API")
@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    /**
     * 게시판 생성
     */
    @Operation(summary = "게시판 생성", description = "새로운 게시판을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = BoardResponseDto.class))),
    })
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(
            @RequestBody BoardRequestDto boardRequestDto,
            @ReqMember Member member) {
        var response = boardService.createBoard(boardRequestDto, member.getId());
        return ResponseDto.ok(response);
    }


    /**
     * 게시판 목록 조회
     */
    @Operation(summary = "게시판 목록 조회", description = "앱에 존재하는 게시판 목록을 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BoardResponseDto.class)))),
    })
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        var response = boardService.getAllBoards();
        return ResponseDto.ok(response);
    }

    /**
     * 게시판내 게시물 모두 조회
     */
    @Operation(summary = "게시판내 게시글 목록 조회", description = "게시판내에 게시글 목록을 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostListResponseDto.class)))),
    })
    @GetMapping("/{boardId}/posts")
    public ResponseEntity<List<PostListResponseDto>> getPostsByBoard(
            @PathVariable("boardId") Long boardId,
            @ReqMember Member member
    ) {
        var response = postService.getPostsByBoard(boardId, member.getId());
        return ResponseDto.ok(response);
    }

    /**
     * 게시글 작성
     */
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
    })
    @PostMapping(value = "/{boardId}/posts", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponseDto> createPost(
            @PathVariable("boardId") Long boardId,
            @ReqMember Member member,
            @ModelAttribute PostRequestDto postRequestDto
    ) throws IOException {
        var response = postService.createPost(boardId, member.getId(), postRequestDto);
        return ResponseDto.created(response);
    }

}
