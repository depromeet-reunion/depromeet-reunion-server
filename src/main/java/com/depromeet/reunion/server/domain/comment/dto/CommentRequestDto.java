package com.depromeet.reunion.server.domain.comment.dto;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Post;
import lombok.Data;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;

    public Comment toEntity(Post post, Member member) {
        return Comment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
    }
}
