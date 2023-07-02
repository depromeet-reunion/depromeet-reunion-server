package com.depromeet.reunion.server.domain.comment.dto.request;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "댓글 작성 모델")
public class CommentRequestDto {
    @Schema(description = "댓글 내용")
    private String content;

    public Comment toEntity(Post post, Member member) {
        return Comment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
    }
}
