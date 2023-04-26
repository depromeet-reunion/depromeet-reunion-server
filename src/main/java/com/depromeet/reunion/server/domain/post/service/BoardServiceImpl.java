package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.post.dto.BoardResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    @Override
    public List<BoardResponseDto> getAllBoards() {
        List< Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto getBoardById(Long id) {
        return null;
    }
}
