package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.post.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    List<BoardResponseDto> getAllBoards();
    BoardResponseDto getBoardById(Long id);
}
