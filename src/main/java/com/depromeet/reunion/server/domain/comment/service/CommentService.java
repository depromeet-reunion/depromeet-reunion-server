package com.depromeet.reunion.server.domain.comment.service;

import com.depromeet.reunion.server.domain.comment.dto.request.CommentRequestDto;
import com.depromeet.reunion.server.domain.comment.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> getCommentsByPost(Long postId);
    CommentResponseDto createComment(Long postId, Long memberId, CommentRequestDto commentRequestDto);
}
