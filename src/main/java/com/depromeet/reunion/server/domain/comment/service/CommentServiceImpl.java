package com.depromeet.reunion.server.domain.comment.service;

import com.depromeet.reunion.server.domain.comment.dto.request.CommentRequestDto;
import com.depromeet.reunion.server.domain.comment.dto.response.CommentResponseDto;
import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.comment.repository.CommentRepository;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.depromeet.reunion.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<CommentResponseDto> getCommentsByPost(Long postId) {
        // postId 있는지 확인
        findPost(postId);
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentResponseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto createComment(Long postId, Long memberId, CommentRequestDto commentRequestDto) {
        Post post = findPost(postId);
        Member member = findMember(memberId);
        Comment comment = commentRepository.save(commentRequestDto.toEntity(post, member));
        return CommentResponseDto.fromEntity(comment);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }
}
