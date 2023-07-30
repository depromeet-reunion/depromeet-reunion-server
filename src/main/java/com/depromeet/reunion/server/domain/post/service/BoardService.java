package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.post.dto.request.BoardRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.BoardResponseDto;

import java.util.List;

public interface BoardService {
    List<BoardResponseDto> getAllBoards();
    BoardResponseDto getBoardById(Long id);

    BoardResponseDto createBoard(BoardRequestDto boardRequestDto, Long id);
}
