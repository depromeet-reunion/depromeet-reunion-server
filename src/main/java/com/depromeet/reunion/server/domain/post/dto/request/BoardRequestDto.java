package com.depromeet.reunion.server.domain.post.dto.request;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
@Getter
@Schema(description = "게시판 작성 모델")
public class BoardRequestDto {
    @Schema(description = "게시판 이름")
    private String boardName;

    public Board toEntity(Member member) {
        return Board.builder()
                .boardName(boardName)
                .member(member)
                .build();
    }
}

