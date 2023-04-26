package com.depromeet.reunion.server.domain.comment.dto;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.dto.MemberSimpleResponseDto;
import com.depromeet.reunion.server.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private MemberSimpleResponseDto member;

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .member(MemberSimpleResponseDto.of(comment.getMember()))
                .build();
    }
}
