package com.depromeet.reunion.server.domain.post.dto;


import com.depromeet.reunion.server.domain.comment.dto.CommentResponseDto;
import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.dto.MemberSimpleResponseDto;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import com.depromeet.reunion.server.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberSimpleResponseDto member;
    private List<ImageResponseDto> imageFiles;
    private int likeCnt;
    private int commentCnt;
    private List<CommentResponseDto> comments;

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageFiles(post.getImageFiles().stream().map(ImageResponseDto::of).collect(Collectors.toList()))
                .createdAt(post.getCreatedAt())
                .member(MemberSimpleResponseDto.of(post.getMember()))
                .likeCnt(post.getPostLikes().size())
                .commentCnt(post.getComments().size())
                .comments(post.getComments().stream().map(CommentResponseDto::of).collect(Collectors.toList()))
                .build();
    }
//    public PostResponseDto(Post post) {
//        this.id = post.getId();
//        this.title = post.getTitle();
//        this.content = post.getContent();
//        this.member = MemberSimpleResponseDto.of(post.getMember());
//        this.comments = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
//    }
}
