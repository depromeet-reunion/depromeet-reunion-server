package com.depromeet.reunion.server.domain.comment.dto.response;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private MemberResponseDto member;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .member(MemberResponseDto.fromEntity(comment.getMember()))
                .build();
    }
}
