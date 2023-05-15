package com.depromeet.reunion.server.domain.post.dto.response;

import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 게시글 목록 조회할 때 쓸 dto
 */
@Getter
@Builder
public class PostListResponseDto {
    private String id;
    private String title;
    private String content;
    private MemberResponseDto member;
    private Optional<String> mainImgUrl;
    private int likeCnt;
    private int commentCnt;
    private LocalDateTime createdAt;

    public static PostListResponseDto fromEntity(Post post) {
        // 메인이미지가 지정되어 있으면 이미지경로 반환
        // 아니면 null 반환
        Optional<String> mainImgUrl = post.getImageFiles()
                .stream()
                .filter(imageFile -> imageFile.isMain())
                .findFirst()
                .map(imageFile -> imageFile.getImgUrl());

        return PostListResponseDto.builder()
                .id(post.getId().toString())
                .title(post.getTitle())
                .content(post.getContent())
                .mainImgUrl(mainImgUrl)
                .createdAt(post.getCreatedAt())
                .member(MemberResponseDto.fromEntity(post.getMember()))
                .likeCnt(post.getLikeCount())
                .commentCnt(post.getCommentCount())
                .build();
    }
}
