package com.depromeet.reunion.server.domain.post.dto.response;

import com.depromeet.reunion.server.domain.post.entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "게시판 조회 응답 모델")
public class BoardResponseDto {

    private String id;
    @Schema(description = "게시판 이름")
    private String boardName;

    public BoardResponseDto(Board board) {
        this.id = String.valueOf(board.getId());
        this.boardName = board.getBoardName();
    }
}

