package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.post.dto.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.PostResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;

import java.util.List;
public interface PostService {
    // 게시판내에 게시글 모두 조회
    List<PostListResponseDto> getPostsByBoard(Long boardId);

    // 게시글 조회
    PostResponseDto getPostById(Long postId);

    // 게시글 등록
    PostResponseDto createPost(Long boardId, Long memberId, PostRequestDto postSaveDto);

    // 게시글 수정
    void updatePost(Long id, PostRequestDto postSaveDto);

    // 게시글 삭제
    void deletePost(Long id);

    // 게시글 좋아요
    void likePost(Long postId, Long memberId);

    // 내가 쓴 게시글 조회
    List<PostListResponseDto> getMyPosts(Long memberId);
}
