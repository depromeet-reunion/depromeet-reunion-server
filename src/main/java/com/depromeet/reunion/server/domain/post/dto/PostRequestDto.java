package com.depromeet.reunion.server.domain.post.dto;

import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.Post;
import lombok.Data;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;

    public Post toEntity(Member member, Board board) {
        return Post.builder()
                .title(title)
                .content(content)
                .board(board)
                .member(member)
                .build();
    }
}
