package com.depromeet.reunion.server.domain.comment.dto.response;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {
    @Schema(description = "댓글 id")
    private String id;
    @Schema(description = "댓글 내용")
    private String content;
    @Schema(description = "작성 일시")
    private LocalDateTime createdAt;
    @Schema(description = "댓글 작성자 정보")
    private MemberResponseDto member;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(String.valueOf(comment.getId()))
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .member(MemberResponseDto.fromEntity(comment.getMember()))
                .build();
    }
}
