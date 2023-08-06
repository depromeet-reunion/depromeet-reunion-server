package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.domain.post.dto.request.BoardRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.BoardResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getAllBoards() {
        List< Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardResponseDto::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, Long memberId) {
        Member member = validateMember(memberId);
        Board board = boardRepository.save(boardRequestDto.toEntity(member));
        return new BoardResponseDto(board);
    }

    private Member validateMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }
}
