package com.depromeet.reunion.server.domain.post.dto;

import com.depromeet.reunion.server.domain.comment.dto.CommentResponseDto;
import com.depromeet.reunion.server.domain.member.dto.MemberSimpleResponseDto;
import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import com.depromeet.reunion.server.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 게시글 목록 조회할 때 쓸 dto
@Getter
@Builder
public class PostListResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberSimpleResponseDto member;
    private Optional<String> mainImgUrl;
    private int likeCnt;
    private int commentCnt;

    public static PostListResponseDto of(Post post) {
        // 메인이미지가 지정되어 있으면 이미지경로 반환
        // 아니면 null 반환
        Optional<String> mainImgUrl = post.getImageFiles()
                .stream()
                .filter(imageFile -> imageFile.isMain())
                .findFirst()
                .map(imageFile -> imageFile.getImgUrl());

        return PostListResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .mainImgUrl(mainImgUrl)
                .createdAt(post.getCreatedAt())
                .member(MemberSimpleResponseDto.of(post.getMember()))
                .likeCnt(post.getPostLikes().size())
                .commentCnt(post.getComments().size())
                .build();
    }
}
