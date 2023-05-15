package com.depromeet.reunion.server.domain.post.controller;

import com.depromeet.reunion.server.domain.common.ApiResponse;
import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
import com.depromeet.reunion.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    final private PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("postId") Long postId) {
        return ApiResponse.ok(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") Long postId,
                                           @RequestPart(value = "postRequest") PostRequestDto postRequestDto,
                                           @RequestPart(value = "imageFiles", required = false) List<MultipartFile> multipartFiles) {
        postService.updatePost(postId, postRequestDto);
        return ApiResponse.noContent();
    }

    @PostMapping("/{postId}/like/{memberId}")
    public ResponseEntity<Void> likePost(@PathVariable("postId") Long postId, @RequestPart(value = "image", required = false) List<MultipartFile> images, @PathVariable("memberId") Long memberId) {
        postService.likePost(postId, memberId);
        return ApiResponse.noContent();

    }

    @GetMapping("/me")
    public ResponseEntity<List<PostListResponseDto>> getMyPosts(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(postService.getMyPosts(memberId));
    }
}
