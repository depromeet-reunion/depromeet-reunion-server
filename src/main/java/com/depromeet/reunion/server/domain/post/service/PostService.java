package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface PostService {
    /**
     * 게시판내에 게시글 모두 조회
     */
    List<PostListResponseDto> getPostsByBoard(Long boardId, Long memberId);

    /**
     * 게시글 상세 조회
     */
    PostResponseDto getPostById(Long postId, Long memberId);

    /**
     * 게시글 등록
     */
    PostResponseDto createPost(Long boardId, Long memberId, PostRequestDto postRequestDto) throws IOException;

    /**
     * 게시글 수정
     */
    void updatePost(Long postId, Long memberId, PostRequestDto postSaveDto);

    /**
     * 게시글 삭제
     */
    void deletePost(Long postId, Long memberId);

    /**
     * 게시글 좋아요
     */
    void likePost(Long postId, Long memberId);

    /**
     * 내가 쓴 게시글 조회
     */
    List<PostListResponseDto> getMyPosts(Long memberId);
}