package com.depromeet.reunion.server.domain.post.dto.response;


import com.depromeet.reunion.server.domain.comment.dto.response.CommentResponseDto;
import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;
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
    @Schema(description = "첨부 이미지 파일 리스트, 이미지 없으면 빈 배열 반환")
    private List<ImageResponseDto> imageFiles;
    @Schema(description = "좋아요 수")
    private int likeCnt;
    @Schema(description = "댓글 수")
    private int commentCnt;
    @Schema(description = "댓글 목록 반환")
    private List<CommentResponseDto> comments;

    public static PostResponseDto fromEntity(Post post) {

        List<ImageResponseDto> imageFiles = Optional.ofNullable(post.getImageFiles())
                .orElse(Collections.emptyList())
                .stream()
                .map(ImageResponseDto::fromEntity)
                .toList();

        return PostResponseDto.builder()
                .id(String.valueOf(post.getId()))
                .title(post.getTitle())
                .content(post.getContent())
                .imageFiles(imageFiles)
                .createdAt(post.getCreatedAt())
                .member(MemberResponseDto.fromEntity(post.getMember()))
                .likeCnt(post.getLikeCount())
                .commentCnt(post.getCommentCount())
                .comments(post.getComments().stream().map(CommentResponseDto::fromEntity).toList())
                .build();
    }
}
