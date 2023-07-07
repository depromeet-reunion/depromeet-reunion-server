package com.depromeet.reunion.server.domain.post.dto.response;

import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 게시글 목록 조회할 때 쓸 dto
 */
@Getter
@Builder
@Schema(description = "게시글 다건 조회 모델")
public class PostListResponseDto {
    @Schema(description = "게시글 id")
    private String id;
    @Schema(description = "게시글 제목")

    private String title;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "작성자 정보")
    private MemberResponseDto member;
    @Schema(description = "대표이미지 (썸네일), 없으면 null")
    @Nullable
    private String imgUrl;
    @Schema(description = "좋아요 수")
    private int likeCnt;
    @Schema(description = "댓글 수")
    private int commentCnt;
    @Schema(description = "작성 일시")
    private LocalDateTime createdAt;

    public static PostListResponseDto fromEntity(Post post) {

        PostListResponseDto.PostListResponseDtoBuilder builder = PostListResponseDto.builder()
                .id(String.valueOf(post.getId()))
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .member(MemberResponseDto.fromEntity(post.getMember()))
                .likeCnt(post.getLikeCount())
                .commentCnt(post.getCommentCount());

        if (post.getImageFile() != null) {
            builder.imgUrl(post.getImageFile().getImgUrl());
        } else {
            builder.imgUrl("");
        }

        return builder.build();
    }
}