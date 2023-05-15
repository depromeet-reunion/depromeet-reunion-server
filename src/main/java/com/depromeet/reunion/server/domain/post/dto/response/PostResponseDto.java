package com.depromeet.reunion.server.domain.post.dto.response;


import com.depromeet.reunion.server.domain.comment.dto.response.CommentResponseDto;
import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberResponseDto member;
    private List<ImageResponseDto> imageFiles;
    private int likeCnt;
    private int commentCnt;
    private List<CommentResponseDto> comments;

    public static PostResponseDto fromEntity(Post post) {

        List<ImageResponseDto> imageFiles = Optional.ofNullable(post.getImageFiles())
                .orElse(Collections.emptyList())
                .stream()
                .map(ImageResponseDto::fromEntity)
                .toList();

        return PostResponseDto.builder()
                .id(post.getId())
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
