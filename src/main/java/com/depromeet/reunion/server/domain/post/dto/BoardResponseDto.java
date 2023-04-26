package com.depromeet.reunion.server.domain.post.dto;

import com.depromeet.reunion.server.domain.post.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String boardName;


    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
    }
}

