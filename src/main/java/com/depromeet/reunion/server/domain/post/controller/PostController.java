package com.depromeet.reunion.server.domain.post.controller;

import com.depromeet.reunion.server.domain.common.ResponseDto;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Tag(name = "Post Controller", description = "게시글 API")
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    final private PostService postService;
    @Operation(summary = "게시글 단건 조회", description = "게시글 내용과 댓글 목록을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
    })
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("postId") Long postId, @ReqMember Member member) {
        return ResponseDto.ok(postService.getPostById(postId, member.getId()));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정하고 수정된 내용을 반환합니다. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updatePost(@PathVariable("postId") Long postId,
                                           @ReqMember Member member,
//                                           @PathVariable("memberId") Long memberId,
                                           @RequestPart(value = "postRequest")  PostRequestDto postRequestDto,
                                           @RequestPart(value = "imageFile", required = false) MultipartFile multipartFile) {
        postService.updatePost(postId, member.getId(), postRequestDto, multipartFile);
        return ResponseDto.noContent();
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId,
                                           @ReqMember Member member
                                           ){
        postService.deletePost(postId, member.getId());
        return ResponseDto.noContent();
    }


    @Operation(summary = "게시글 좋아요/ 좋아요 취소", description = "좋아요 실행, 좋아요 누른 게시물에 좋아요 -> 좋아요 취소 실행. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success")
    })
    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable("postId") Long postId,
                                         @ReqMember Member member
//                                         @PathVariable("memberId") Long memberId
                                         ) {
        postService.likePost(postId, member.getId());
        return ResponseDto.noContent();
    }

    @Operation(summary = "내가 작성한 게시글 목록", description = "내가 작성한 게시글 목록을 반환합니다. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostListResponseDto.class)))),
    })
    @GetMapping("/me")
    public ResponseEntity<List<PostListResponseDto>> getMyPosts(
            @ReqMember Member member
//            @PathVariable("memberId") Long memberId
            ) {
        return ResponseDto.ok(postService.getMyPosts(member.getId()));
    }
}