package com.depromeet.reunion.server.domain.post.dto.response;


import com.depromeet.reunion.server.domain.comment.dto.response.CommentResponseDto;
import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
@Schema(description = "게시글 단건 조회 모델")
public class PostResponseDto {
    @Schema(description = "게시글 id")
    private String id;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "작성 일시")
    private LocalDateTime createdAt;
    @Schema(description = "작성자")
    private MemberResponseDto member;
    @Schema(description = "본인이 작성한 글인지 여부")
    private Boolean isMine;
    @Schema(description = "첨부 이미지 파일 리스트, 이미지 없으면 빈 배열 반환")
    private String imgUrl;
    @Schema(description = "좋아요 수")
    private int likeCnt;
    @Schema(description = "댓글 수")
    private int commentCnt;

    public static PostResponseDto fromEntity(Post post, Long memberId) {
        // post.getMember().getId()와 memberId가 일치하는지 확인하여 isMine 값을 설정
        boolean isMine = post.getMember().getId().equals(memberId);
        PostResponseDto.PostResponseDtoBuilder builder = PostResponseDto.builder()
                .id(String.valueOf(post.getId()))
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .member(MemberResponseDto.fromEntity(post.getMember()))
                .isMine(isMine)
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